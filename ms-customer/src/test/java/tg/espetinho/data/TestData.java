package tg.espetinho.data;

import tg.espetinho.entity.Customer;
import tg.espetinho.web.dto.*;

public class TestData {
    public static final Customer CUSTOMER = new Customer(
            "12345678901",
            "John Doe",
            "123456789"
    );
    public static final FindRequestDTO FIND_REQUEST_DTO = new FindRequestDTO(
            "12345678901"
    );
    public static final FindRequestDTO FIND_EMPTY_REQUEST_DTO = new FindRequestDTO(
            ""
    );
    public static final FindResponseDTO FIND_RESPONSE_DTO = new FindResponseDTO(
            "John Doe",
            "12345678901"
    );
    public static final CreateRequestDTO CREATE_REQUEST_DTO = new CreateRequestDTO(
            "John Doe",
            "12345678901",
            "123456789"
    );
    public static final CreateResponseDTO CREATE_RESPONSE_DTO = new CreateResponseDTO(
            "John Doe",
            "12345678901"
    );
    public static final UpdateRequestDTO UPDATE_REQUEST_DTO = new UpdateRequestDTO(
            "123456789",
            "Jane Doe",
            "987654321"
    );
    public static final DeleteRequestDTO DELETE_REQUEST_DTO = new DeleteRequestDTO(
            "12345678901"
    );

}
