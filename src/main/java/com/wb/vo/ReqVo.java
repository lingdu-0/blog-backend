package com.wb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqVo {
    private Integer id;
    private String title;
    private String content;
    private String describe;
    private Integer category;
    private String name;
    private Integer state;
    private String address;
    private MultipartFile file;
}
