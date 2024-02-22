package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {
    private String itemName;
    private String entpName;
    private Long itemSeq;
    private String efcyQesitm;
    private String useMethodQesitm;
    private String atpnQesitm;
    private String depositMethodQesitm;
    private String intrcQesitm;
    private String seQesitm;
    private String atpnWarnQesitm;
    private String itemImage;
}
