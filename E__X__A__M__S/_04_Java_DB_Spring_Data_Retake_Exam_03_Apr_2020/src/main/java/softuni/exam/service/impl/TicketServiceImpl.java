package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTicketDTO;
import softuni.exam.models.dto.ImportTicketRootDTO;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Plane;
import softuni.exam.models.entity.Ticket;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String TICKET_FILE_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final TownRepository townRepository;

    private final PassengerRepository passengerRepository;

    private final PlaneRepository planeRepository;

    private final ModelMapper modelMapper;
    private final XmlParserImpl xmlParser;
    private final ValidationUtilImpl validator;

    public TicketServiceImpl(TicketRepository ticketRepository, TownRepository townRepository, PassengerRepository passengerRepository,
                             PlaneRepository planeRepository, ModelMapper modelMapper, XmlParserImpl xmlParser, ValidationUtilImpl validator) {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKET_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException {
        ImportTicketRootDTO ticketsRootDTOs = this.xmlParser.fromFile(TICKET_FILE_PATH, ImportTicketRootDTO.class);
        return ticketsRootDTOs.getTickets().stream().map(this::importTicket).collect(Collectors.joining("\n"));

    }

    private String importTicket(ImportTicketDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Ticket";
        }

        Optional<Ticket> optTicket = this.ticketRepository.findBySerialNumber(dto.getSerialNumber());


        if (optTicket.isPresent()) {
            return "Invalid Ticket";
        }


        Optional<Passenger> passenger = this.passengerRepository.findByEmail(dto.getPassenger().getEmail());
        Optional<Plane> plane = this.planeRepository.findByRegisterNumber(dto.getPlane().getRegisterNumber());
        Optional<Town> townTo = this.townRepository.findByName(dto.getToTown().getName());
        Optional<Town> townFrom = this.townRepository.findByName(dto.getFromTown().getName());

        Ticket ticket = this.modelMapper.map(dto, Ticket.class);


        if (townFrom.isEmpty() || townTo.isEmpty() || plane.isEmpty() || passenger.isEmpty()) {
            return "Invalid Ticket";

        }

        ticket.setPassenger(passenger.get());
        ticket.setPlane(plane.get());
        ticket.setToTown(townTo.get());
        ticket.setFromTown(townFrom.get());


        this.ticketRepository.save(ticket);

        return String.format("Successfully imported Ticket %s - %s", dto.getFromTown().getName(), dto.getToTown().getName());
    }
}
