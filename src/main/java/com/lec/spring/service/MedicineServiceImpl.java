package com.lec.spring.service;

import com.lec.spring.domain.Medicine;
import com.lec.spring.repository.MedicineRepository;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {


    private MedicineRepository medicineRepository;

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;

    private String apikey ="qldguqvXIu42xCDyOSTvTu+nmFiLjCH4ZUymYcnYP1bKYMt0XyZtC0PD+etdt+n9Ylp5NRWM1+pax7+jZZ6kow==";//"qldguqvXIu42xCDyOSTvTu%2BnmFiLjCH4ZUymYcnYP1bKYMt0XyZtC0PD%2Betdt%2Bn9Ylp5NRWM1%2Bpax7%2BjZZ6kow%3D%3D";
    private String url1 = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=";
    private String url2 =  "&pageNo=1&numOfRows=100&type=json";

    @Autowired
    public MedicineServiceImpl(SqlSession sqlSession){
        medicineRepository = sqlSession.getMapper(MedicineRepository.class);
        System.out.println("생성"); // 간단한 출력문
    }

    @Scheduled(fixedDelay = 3600000)
    public void saveApi() throws IOException {
        for (int pageNo = 1; pageNo <= 50; pageNo++) {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=qldguqvXIu42xCDyOSTvTu%2BnmFiLjCH4ZUymYcnYP1bKYMt0XyZtC0PD%2Betdt%2Bn9Ylp5NRWM1%2Bpax7%2BjZZ6kow%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("entpName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*업체명*/
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제품명*/
            urlBuilder.append("&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품목기준코드*/
            urlBuilder.append("&" + URLEncoder.encode("efcyQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약의 효능은 무엇입니까?*/
            urlBuilder.append("&" + URLEncoder.encode("useMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떻게 사용합니까?*/
            urlBuilder.append("&" + URLEncoder.encode("atpnWarnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약을 사용하기 전에 반드시 알아야 할 내용은 무엇입니까?*/
            urlBuilder.append("&" + URLEncoder.encode("atpnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약의 사용상 주의사항은 무엇입니까?*/
            urlBuilder.append("&" + URLEncoder.encode("intrcQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약을 사용하는 동안 주의해야 할 약 또는 음식은 무엇입니까?*/
            urlBuilder.append("&" + URLEncoder.encode("seQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떤 이상반응이 나타날 수 있습니까?*/
            urlBuilder.append("&" + URLEncoder.encode("depositMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떻게 보관해야 합니까?*/
            urlBuilder.append("&" + URLEncoder.encode("openDe", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공개일자*/
            urlBuilder.append("&" + URLEncoder.encode("updateDe", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*수정일자*/
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default:xml*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            // System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            //System.out.println(sb.toString());
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject body = jsonObject.getJSONObject("body");
            JSONArray items = body.getJSONArray("items");
            List<Medicine> medicineList = new ArrayList<>();

            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);

                    // Medicine 객체 생성 및 데이터 설정
                    Medicine medicine = new Medicine();
                    medicine.setItemName(item.getString("itemName")); // 예시: JSON 데이터에서 약품 이름을 가져와 설정
                    medicine.setEntpName(item.getString("entpName"));
                    medicine.setItemSeq(item.getString("itemSeq"));
                    medicine.setEfcyQesitm(item.optString("efcyQesitm", null)); // 기본값으로 null 설정
                    medicine.setUseMethodQesitm(item.optString("setUseMethodQesitm", null));
                    medicine.setAtpnQesitm(item.optString("atpnQesitm", null));
                    medicine.setDepositMethodQesitm(item.optString("depositMethodQesitm", null));
                    medicine.setIntrcQesitm(item.optString("intrcQesitm", null));
                    medicine.setSeQesitm(item.optString("seQesitm", null));
                    medicine.setAtpnWarnQesitm(item.optString("atpnWarnQesitm", null));
                    medicine.setItemImage(item.optString("itemImage", null));
                    // 다른 필드도 필요에 따라 JSON 데이터에서 가져와 설정

                    // Medicine 객체를 리스트에 추가
                    medicineList.add(medicine);
                } catch (JSONException e) {
                    // JSON 데이터를 파싱하다가 예외가 발생한 경우 처리
                    e.printStackTrace(); // 또는 로그를 남기거나 다른 예외 처리 방법을 사용
                }
            }

            for (Medicine medicine : medicineList) {
                medicineRepository.save(medicine);
            }
        }
    }
        @Override
        public List<Medicine> list () {
            return medicineRepository.findAll();
        }

        @Override
        public int save (Medicine medicine){
            medicineRepository.save(medicine);
            return 1;
        }

        // pagination
        @Override
        public List<Medicine> list (Integer page, Model model){
            // 현재 페이지 parameter
            if (page == null) page = 1;  // 디폴트는 1page
            if (page < 1) page = 1;

            // 페이징
            // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
            // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
            HttpSession session = U.getSession();
            Integer writePages = (Integer) session.getAttribute("writePages");
            if (writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
            Integer pageRows = (Integer) session.getAttribute("pageRows");
            if (pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

            // 현재 페이지 번호 -> session 에 저장
            session.setAttribute("page", page);

            long cnt = medicineRepository.countAll();   // 글 목록 전체의 개수
            int totalPage = (int) Math.ceil(cnt / (double) pageRows);   // 총 몇 '페이지' ?

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
            int startPage = 0;
            int endPage = 0;

            // 해당 페이지의 글 목록
            List<Medicine> list = null;

            if (cnt > 0) {  // 데이터가 최소 1개 이상 있는 경우만 페이징
                //  page 값 보정
                if (page > totalPage) page = totalPage;

                // 몇번째 데이터부터 fromRow
                int fromRow = (page - 1) * pageRows;

                // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
                startPage = (((page - 1) / writePages) * writePages) + 1;
                endPage = startPage + writePages - 1;
                if (endPage >= totalPage) endPage = totalPage;

                // 해당페이지의 글 목록 읽어오기
                list = medicineRepository.selectFromRow(fromRow, pageRows);
                model.addAttribute("list", list);
            } else {
                page = 0;
            }

            model.addAttribute("cnt", cnt);  // 전체 글 개수
            model.addAttribute("page", page); // 현재 페이지
            model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
            model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

            // [페이징]
            model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
            model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
            model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
            model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

            return list;
        }
    }
