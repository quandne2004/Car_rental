package QuanDen.demo.entity;


import QuanDen.demo.dto.CommentDto;
import QuanDen.demo.enums.CommentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Lob
    private String body;
    private Date createdDate;


    private int rating;


    private CommentStatus commentStatus;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;



    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "car_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;



    public CommentDto getCommentDto(){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(id);
        commentDto.setBody(body);
        commentDto.setUserId(user.getId());
        commentDto.setRating(rating);
        commentDto.setCarId(car.getId());
        commentDto.setCreatedDate(createdDate);
        commentDto.setUsername(user.getName());
        commentDto.setCommentStatus(commentStatus);
        return commentDto;
    }


}
