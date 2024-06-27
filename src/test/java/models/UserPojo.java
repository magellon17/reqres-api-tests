package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
