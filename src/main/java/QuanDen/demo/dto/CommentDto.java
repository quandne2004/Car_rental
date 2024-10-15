package QuanDen.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long id;
    private String body;
    private Date createdDate;
    private int rating;
    private Long userId;
    private Long carId;
    private String username;
}
