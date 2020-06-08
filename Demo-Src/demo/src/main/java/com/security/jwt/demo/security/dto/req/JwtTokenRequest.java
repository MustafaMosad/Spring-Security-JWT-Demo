package com.security.jwt.demo.security.dto.req;
import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String email;
    private String password;

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

  
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

