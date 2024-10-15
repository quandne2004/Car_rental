package QuanDen.demo.dto;


import lombok.Data;

@Data
public class SearchCarDto {
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private Long numberSeat;
}
