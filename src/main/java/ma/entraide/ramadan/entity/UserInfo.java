package ma.entraide.ramadan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String roles;
    private String password;
    public String getTele() {
		return tele;
	}
	public UserInfo() {
		super();
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	//private String Coordination;
    private String tele;
    //private String delegation;
    
    @ManyToOne
    @JoinColumn(name = "delegation_id")
    private Delegation delegation;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*public String getCoordination() {
		return Coordination;
	}
	public void setCoordination(String coordination) {
		Coordination = coordination;
	}
	public String getDelegation() {
		return delegation;
	}
	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}*/
	public String getName() {
		return name;
	}
	public Delegation getUnite() {
		return delegation;
	}
	public void setUnite(Delegation delegation) {
		this.delegation = delegation;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
}
