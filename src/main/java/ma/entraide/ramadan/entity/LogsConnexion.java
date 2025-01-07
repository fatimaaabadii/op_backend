package ma.entraide.ramadan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.entraide.ramadan.entity.UserInfo;

import java.util.Date;

@Entity
@Data
public class LogsConnexion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logs_id")
    private Long id;

    private Date dateLogin;

    private String ipAdresse;

    private String accountEmail;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}

	public String getIpAdresse() {
		return ipAdresse;
	}

	public void setIpAdresse(String ipAdresse) {
		this.ipAdresse = ipAdresse;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public UserInfo getUser() {
		return user;
	}

	public LogsConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	private String device;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserInfo user;
}
