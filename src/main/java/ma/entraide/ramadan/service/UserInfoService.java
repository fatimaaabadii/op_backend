package ma.entraide.ramadan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.entraide.ramadan.entity.UserInfo;
import ma.entraide.ramadan.repository.UserInfoRepository;

import java.util.List;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
    }
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
    public List<UserInfo> getAllUser(){
         return userInfoRepository.findAll();
    }
    public UserInfo getUser(Integer id){
        return userInfoRepository.findById(id).get();
    }
    @Transactional
    public String deleteUser(Long id){
        userInfoRepository.deleteById(id);
        return "Employé supprimé";
    }
	
    public UserInfo saveUser(UserInfo user){
        return userInfoRepository.save(user);
    }

    public UserInfo updateUser(Long id,UserInfo newUser){
    	UserInfo user = userInfoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Emplyé non Trouvé"));
    	user.setName(newUser.getName());
    	user.setEmail(newUser.getEmail());
    	user.setRoles(newUser.getRoles());
    	user.setPassword(newUser.getPassword());
    	user.setUnite(newUser.getUnite());
    	//user.setDelegation(newUser.getDelegation());
    	//user.setCoordination(newUser.getCoordination());
        user.setTele(newUser.getTele());
    	
    	userInfoRepository.save(user);
        return user;
    }
	public Optional<UserInfo> getCurrentUser(String userName) {
		
		return userInfoRepository.findByEmail(userName);
	}
	
	
	
	  public UserInfo findUserByUsername(String email) throws UsernameNotFoundException {
	        Optional<UserInfo> userInfoOp = userInfoRepository.findByEmail(email);
	        UserInfo userInfo = null;
	        if(userInfoOp.isPresent()){
	            userInfo = userInfoOp.get();
	        }
	        else{
	            throw new UsernameNotFoundException("User not found");
	        }
	        return userInfo;
	    }
	
	
	
	
	public boolean updatePassword(UserInfo user, String oldPassword, String newPassword, String confirmPassword) {
	    if (userInfoRepository.findById(user.getId()).isEmpty()) {
	        return false;
	    }

	    UserInfo existingUser = userInfoRepository.findById(user.getId()).get();

	    // Vérification de l'ancien mot de passe
	    if (!passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
	        return false;
	    }

	    // Vérification de la correspondance entre le nouveau mot de passe et sa confirmation
	    if (!newPassword.equals(confirmPassword)) {
	        return false;
	    }

	    // Hachage du nouveau mot de passe
	    String newPasswordHash = passwordEncoder.encode(newPassword);

	    // Mise à jour du mot de passe dans la base de données
	    existingUser.setPassword(newPasswordHash);
	    userInfoRepository.save(existingUser); 

	    return true;
	}
    


}
