package com.example.study.medel;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data   // lombok을 통해 기본적인 변수와 getter setter가 생성해줌
@AllArgsConstructor  // 모든 매개변수를 받는 메소드를 생성해줌
public class SearchParam {

    private String account;
    private String email;
    private int page;

    //{ "account" : "", "email" : "", "page" : 0} json Library를 통해 json 형태로 바꿔주게됨 (return을 searchParam 자체)

/*    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
*/
}