package me.app.coinwallet.dto;

import lombok.Data;

import java.util.List;
@Data
public class ChartDto {
    private String id;
    private List<List<String>> pointList;
}
