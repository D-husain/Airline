package com.example.demo.Controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Bean.Checkout;
import com.example.demo.Bean.Cities;
import com.example.demo.Bean.Contact;
import com.example.demo.Bean.One_Way_Flight;
import com.example.demo.Bean.Round_Way_Flights;
import com.example.demo.Bean.Services;
import com.example.demo.Bean.User;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Util.UserUtil;
import com.example.demo.service.EmailService;

@Controller
public class UserController {

	Random random= new Random(1000);
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private AdminDao adao;
	
	@Autowired
	private UserUtil uutil;
	
	
	@GetMapping("/")
	public String home(Model m,HttpSession s,@ModelAttribute User u) {
		m.addAttribute("user", u);
		System.out.println("USer "+ u);
		m.addAttribute("flight", adao.showRound_Wayflight());
		m.addAttribute("cities", adao.ShowCities());
		m.addAttribute("services", adao.showservices());
		return "index";
	} 
	
	@GetMapping("/ticket")
	public String ticket(HttpSession s,Model m){
		Round_Way_Flights rwf = adao.getFlightById((int) s.getAttribute("flightId"));
		m.addAttribute("ticke", rwf);
		System.out.println("ticke : " + rwf);
		return "ticket";
	}
/*----------------------------------------------------------------------------------------------------------*/	
	
	@GetMapping("/travel")
	public String getid(@RequestParam("id") int id, Model m,HttpSession session) {
		session.setAttribute("flightsId", id);
		One_Way_Flight flight = adao.getFlightsById(id);
		m.addAttribute("flight", flight);
		return "oneway_passenger_detail";
	}
	
	@GetMapping("/travels")
	public String getids(@RequestParam("id") int id, Model m,HttpSession session) {
		session.setAttribute("flightId", id);
		Round_Way_Flights r=adao.getFlightById(id);
		m.addAttribute("flight", r);
		return "roundway_passenger_detail";
	}
	
	/*@PostMapping("/addcheckout")
	public String addcheckout(@ModelAttribute Checkout c,HttpSession s) {
		//Checkout ca = (Checkout)s.getAttribute("passengerdata");
		//udao.Addcheckout(ca);
		//s.setAttribute("msge", "Sucessfully");
		return"redirect:/pay";
	}*/
	
	@GetMapping("/roundwaypay")
	public String travel2(@ModelAttribute Checkout c, Model m,HttpSession flightidsession) {
		
		m.addAttribute("passengerdetais",c);
		Round_Way_Flights rwf = adao.getFlightById((int) flightidsession.getAttribute("flightId"));
		m.addAttribute("passenger", rwf);
		flightidsession.setAttribute("passengerdata", c);
		udao.Addcheckout(c);
		return "roundway-pay";
	}
	
	@GetMapping("/onewaypay")
	public String pay(@ModelAttribute Checkout c, Model m,HttpSession flightidsession) {
		
		m.addAttribute("passengerdetais",c);
		One_Way_Flight owf= adao.getFlightsById((int) flightidsession.getAttribute("flightsId"));
		m.addAttribute("passenger", owf);
		flightidsession.setAttribute("passengerdata", c);
		udao.Addcheckout(c);
		return "oneway-pay";
	}
	
	@GetMapping("/flights/{id}")
	public String getids(@PathVariable int id, Model m,@ModelAttribute("flight") Round_Way_Flights flight) {
		 flight = adao.getFlightById(id);
		return "redirect:/travel1";
	}
	
/*----------------------------------------------------------------------------------------------------------*/	
	
	@GetMapping("/services")
	public String services(Model m, Services s){
		m.addAttribute("ser", adao.showservices());
		m.addAttribute("img",s.getSimg());
		return "services";
	}
	
/*----------------------------------------------------------------------------------------------------------*/	
	
	@GetMapping("/flights")
    public String findAllFlight(Model model,HttpSession session,Cities c, String source,String dastination,String departuredate,String returndate) {
    
		model.addAttribute("cities", adao.ShowCities());
		
		if (returndate != null ) {
			
			model.addAttribute("alerts","Origin and destination can't be the same");
			
			List<Round_Way_Flights> flight = udao.findallflight(source, dastination, departuredate, returndate);
			    
			    model.addAttribute("flight",flight);
			    
			    
			    model.addAttribute("source",source);
			    model.addAttribute("dastination",dastination);
			    model.addAttribute("departuredate",departuredate);
			    model.addAttribute("returndate",returndate);
			    
			    System.out.println("From: "+source);
			    System.out.println("To: "+dastination);
			    System.out.println("Departuredate : "+departuredate);
			    System.out.println("Return: "+returndate);
			    
			    model.addAttribute("flights", adao.showRound_Wayflight());
			    	
			    return "roundway_flight";
		}else {
			
			List<One_Way_Flight> flight = udao.findallflights(source, dastination, departuredate);
		    
			model.addAttribute("flight",flight);
		    
			model.addAttribute("source",source);
			model.addAttribute("dastination",dastination);
			model.addAttribute("departuredate",departuredate);
		    
		    System.out.println("From: "+source);
		    System.out.println("To: "+dastination);
		    System.out.println("Departuredate : "+departuredate);
		    
		    model.addAttribute("flights", adao.showOneway_Flights());
		    
		    return "oneway_flight";
		}
	}

	
/*----------------------------------------------------------------------------------------------------------*/

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@PostMapping("/addcontact")
	public String addcontact(@ModelAttribute Contact c,HttpSession s ) {
		udao.addcontact(c);
		s.setAttribute("msg", "Contact Added Sucessfully");
		return"redirect:/contact";
	}
	
	@GetMapping("/admin-view-contact")
	public String viewcontact(Model m) {
		m.addAttribute("contact", udao.showcontact());
		return "admin-view-contact";
	}
	
	@GetMapping("/admin-edit-contact")
	public String edit(@RequestParam("id") int id, Model m) {
		Contact c = udao.getContactById(id);
		m.addAttribute("contact", c);
		return "admin-edit-contact";
	}

	@PostMapping("/updatecontact")
	public String updatecontact(@ModelAttribute Contact c, HttpSession session) {
		udao.addcontact(c);
		session.setAttribute("msg", "Contact Update Sucessfully..");
		return "redirect:/admin-view-contact";
	}

	@GetMapping("/deletecontact/{id}")
	public String deleteEMp(@PathVariable int id, HttpSession session) {

		udao.deletecontact(id);
		session.setAttribute("msg", "Contact Delete Sucessfully..");
		return "redirect:/admin-view-contact";
	}
	
/*----------------------------------------------------------------------------------------------------------*/

	@GetMapping("/register")
	public String register(){
		return"register";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session,Model m){
		
		if(session.getAttribute("loginuser")!=null){
			m.addAttribute("user", "Your are logout!");
//			return "redirect:/login";
		}
		return"login";
	}
	
	
	@PostMapping("/register_user")
	public String register_user(@ModelAttribute("user") User user,@RequestParam("email") String email, HttpSession session) {

		User findemail=uutil.findByEmail(email);
		
		if (findemail !=null) {
			session.setAttribute("msg", "Email id already exists");
			return "redirect:/register";
		}else {
			boolean t = udao.register(user);

			if (t == true) {
				session.setAttribute("data", "Successfully_Inserted");
			} else {
				session.setAttribute("data", "Data_Not_Inserted");
			}
		}
		
		return "redirect:/login";
	}
	
	@PostMapping("/login_user")
	public String fechAdmimnData(@RequestParam("name") String user_name,
			@RequestParam("password") String password,HttpSession s,Model m ) {
		List<User> list = udao.fechAllUser();

		String page_move = "redirect:/login";

		for (User user : list) {
			if (user_name.equals(user.getName()) && password.equals(user.getPassword())) {
				s.setAttribute("login", "userlogin");
				page_move = "redirect:/";
			}else if (!user_name.equals(user.getName()) && !password.equals(user.getPassword())) {
				//m.addAttribute("user", "Invalid email/password");
				s.setAttribute("user", "Invalid email/password");
			}
		}
		return page_move;
	}
	
	@GetMapping("/user-logout")
	public String login_animation(HttpSession s,Model m){
		s.invalidate();
		s.setAttribute("user", "Logout Sucessfully..");
		return "redirect:/login";
	}
	
	@GetMapping("/admin-view-inquiry")
	public String admininqu(Model m){
		m.addAttribute("user", udao.fechAllUser());
		return"admin-view-inquiry";
	}
	
	@GetMapping("/deleteuser/{id}")
	public String deleteuser(@PathVariable int id, HttpSession session) {

		udao.deleteuser(id);
		session.setAttribute("user", "User Delete Sucessfully..");
		return "redirect:/admin-view-inquiry";
	}
	
/*----------------------------------------------------------------------------------------------------------*/	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/forgot")
	public String forgot(){
		return "forgot";
	}
	
	
	@PostMapping("/send-otp")
	public String sendotp(@RequestParam("email") String email,HttpSession session){
		
		System.out.println("Email " +email);
		
		int otp =random.nextInt(99999);
		
		System.out.println("Otp "+ otp);
		
		
		
		//Your OTP is: "+otp+" Plese Dont's share Otp
		
		String subject="Airline";
		String message=""
				+ "<div style='font-family Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2'>"
				+ " <div style='margin: 0 auto;width:70%;padding:20px 0'>"
				+ "<div style='border-bottom:1px solid #eee'>"
				+ "<a href='' style='font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600'>Welcome Airline</a>"
				+ "</div>"
				+ "  <p style='font-size:1.1em'>Hi,</p>"
				+ "<p>Thank you for You have requested us otp to reset password for your <h1>Airline Account</h1> Use the following OTP to complete your Sign Up procedures. OTP is valid for 5 minutes</p>"
				+ "<h2 style='background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;'>"+otp+"</h2>"
				+ "<p style='font-size:0.9em;'>Regards,<br />Your Airline</p>"
				+ "<hr style='border:none;border-top:1px solid #eee'/>"
				+ " <div style='float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300'>"
				+ "<p>Airline Inc</p>"
				+ "<p>1600 Amphitheatre Parkway</p>"
				+ "<p>Ahmadabad</p>"
				+ "</div>"
				+ "</div>"
				+ "</div>";
		String to=email;
		
		User findemail=uutil.findByEmail(email);
		
		if (findemail !=null) {
			boolean flag = this.emailService.sendEmail(subject, message, to);
			
			if (flag) {
				session.setAttribute("otp", otp);
				session.setAttribute("email", email);
				session.setAttribute("msg", "OTP is sent to your email id");
				return "varify_otp";
			}else {
				
				return "forgot";
			}
		}else {
			session.setAttribute("msg", "This email does not exits!");
			return "redirect:/forgot";
		}
		
		
		
	}
	
	@PostMapping("/varify-otp")
	public String varifyotp(@RequestParam("otp") int otp,HttpSession session){
	
		int myotp=(int) session.getAttribute("otp"); 
		if (myotp==otp) {
			return "reset_password";
		}else {
			session.setAttribute("msg", "Please enter valid Otp");
			return "varify_otp";
		}
	}
	
	@PostMapping("/reset_password")
	public String reset_password(@RequestParam("newpassword") String newpassword,HttpSession session){
		
		String email=(String)session.getAttribute("email");
		User user= this.uutil.getUserByUserName(email);
		
		udao.updatePassword(user, newpassword);
		
		//user.setPassword(this.bCryptPasswordEncoderl.encode(newpassword));
		//this.uutil.save(user);
		
		return "redirect:/login";
	}
}
