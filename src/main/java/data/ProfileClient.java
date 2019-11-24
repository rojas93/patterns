package data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProfileClient {
    private final String city;
    private final String fullName;
    private final String phoneNumber;
}
