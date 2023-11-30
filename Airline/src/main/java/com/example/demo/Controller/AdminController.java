package com.example.demo.Controller;

import java.security.Principal;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Bean.Admin;
import com.example.demo.Bean.Cities;
import com.example.demo.Bean.One_Way_Flight;
import com.example.demo.Bean.Round_Way_Flights;
import com.example.demo.Bean.Services;
import com.example.demo.Bean.User;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Util.AdminUtil;
import com.example.demo.service.EmailService;
import com.example.demo.uplod.Upload_File;

@Controller
public class AdminController {
	
	Random random= new Random(1000);
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void UserController(AdminUtil autil, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.autil = autil;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Autowired
	private Upload_File fileuploadhelper;
	@Autowired
	private AdminDao adao;
	@Autowired
	private UserDao udao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AdminUtil autil;
	
/*--------------------------------------------------------------------------------------------------------------*/	
	
	@GetMapping("/dashboard")
	public String admin(Model m){
		m.addAttribute("admin", adao.fechAllAdmin());
		return"admin_index";
	}
	
	@GetMapping("/admin-forgot")
	public String adminforg(){
		return"admin-forgot-password";
	}
	
	@GetMapping("/admin-view-details")
	public String viewdetail(Model m) {
		m.addAttribute("detail", udao.showcheckout());
		return "admin-view-details";
	}
	
	@GetMapping("/deletedetail")
	public String deletedetail(@RequestParam("id") int id, HttpSession session) {

		udao.deletedetail(id);
		session.setAttribute("user", "User Delete Sucessfully..");
		return "redirect:/admin-view-details";
	}
/*----------------------------------------------------------------------------------------------------------*/	
	
	@GetMapping("/admin-profile")
	public String adminprofile(){
		return"admin-profile";
	}
	
	@PostMapping("/updateprofile")
	public String updateprofile(@ModelAttribute Admin admin, HttpSession session,Model m) {
		adao.adminregister(admin);
		m.addAttribute("admin", admin);
		session.setAttribute("ad", "Profile Update Sucessfully..");
		return "redirect:/admin-profile";
	}
	
/*----------------------------------------------------------------------------------------------------------*/	

	@GetMapping("/admin")
	public String adminlogin(){
		return"admin-login";
	}
	
	@PostMapping("/register_admin")
	public String save_admin_details(@ModelAttribute("admin") Admin admin, Model model) {

		boolean t = adao.adminregister(admin);

		if (t == true) {
			model.addAttribute("data", "Successfully_Inserted");
		} else {
			model.addAttribute("data", "Data_Not_Inserted");
		}

		return "/admin-login";
	}
	
	@PostMapping("/login_admin")
	public String fechAdmimnData(@RequestParam("name") String user_name ,
			@RequestParam("password") String password,
			HttpSession s ) {
		List<Admin> list = adao.fechAllAdmin();

		String page_move = "redirect:/admin";

		for (Admin admin : list) {
			if (user_name.equals(admin.getName()) &&  password.equals(admin.getPassword())) {
				page_move = "redirect:/dashboard";
				
			} else if (user_name.equals(admin.getName())) {
				s.setAttribute("msge", "Invalid password");
				
			} else if (password.equals(admin.getPassword())) {
				s.setAttribute("msge", "Invalid Username");
				
			} else if (!user_name.equals(admin.getName()) && !password.equals(admin.getPassword())) {
				s.setAttribute("msge", "Invalid email/password");
			}
		}
		return page_move;
	}
	
	@GetMapping("/logout")
	public String login_animation(){
		return "redirect:/admin";
	}
	
	@GetMapping("/admin-change-password")
	public String adminchange(){
		return"/admin-change-password";
	}
	
	 @PostMapping("/change-password")
	    public String changePassword(
	            @RequestParam("oldpassword") String oldpassword,
	            @RequestParam("newpassword") String newpassword,
	            Principal principal,
	            HttpSession session,
	            Model model) {

	        // Get the current user's name
	        String name = principal.getName();
	        Admin currentUser = this.autil.getAdminByAdminName(name);

	        // Check if the old password matches the current password
	        if (this.bCryptPasswordEncoder.matches(oldpassword, currentUser.getPassword())) {
	            // Hash and update the password
	            String hashedNewPassword = this.bCryptPasswordEncoder.encode(newpassword);
	            currentUser.setPassword(hashedNewPassword);
	            this.autil.save(currentUser);

	            // Set a success message in the session
	            model.addAttribute("msges", "Your password has been successfully changed");

	            return "redirect:/admin-login";
	        } else {
	            // Set an error message in the session
	            model.addAttribute("msges", "Please enter the correct old password");

	            return "redirect:/admin-change-password";
	        }
	    }
	 
	/* @GetMapping("/admin-edit-profile/{id}")
		public String profilredit(@PathVariable int id, Model m) {
			Admin a = adao.getAdminById(id);
			m.addAttribute("admin", a);
			return "redirect:/admin-profile";
		}*/
	 
	
	 
	 @GetMapping("/admin-forgot-password")
	 public String adminf(){
		 return "admin-forgot-password";
	 }
	 
	 @PostMapping("/admin-send-otp")
		public String adminsendotp(@RequestParam("email") String email,HttpSession session){
			
			System.out.println("Email " +email);
			
			int otp =random.nextInt(99999);
			
			System.out.println("Otp "+ otp);
			
			
			
			//Your OTP is: "+otp+" Plese Dont's share Otp
			
			String subject="Admin Airline";
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
			
			Admin findemail=autil.findByEmail(email);
			
			if (findemail !=null) {
				boolean flag = this.emailService.sendEmail(subject, message, to);
				
				if (flag) {
					session.setAttribute("otp", otp);
					session.setAttribute("email", email);
					session.setAttribute("otps", "OTP is sent to your email id");
					return "admin-varify-otp";
				}else {
					
					return "admin-forgot-password";
				}
			}else {
				session.setAttribute("error", "This email does not exits!");
				return "redirect:/admin-forgot-password";
			}
		}
		
		@PostMapping("/admin-varify-otp")
		public String adminvarifyotp(@RequestParam("otp") int otp,HttpSession session){
		
			int myotp=(int) session.getAttribute("otp"); 
			if (myotp==otp) {
				return "admin-reset-password";
			}else {
				session.setAttribute("otps", "Please enter valid Otp");
				return "admin-varify-otp";
			}
		}
		
		@PostMapping("/admin_reset_password")
		public String admin_reset_password(@RequestParam("newpassword") String newpassword,HttpSession session){
			
			String email=(String)session.getAttribute("email");
			Admin user= this.autil.getAdminByAdminName(email);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newpassword);
	        user.setPassword(encodedPassword);
	         
	        autil.save(user);
			
			//user.setPassword(this.bCryptPasswordEncoderl.encode(newpassword));
			//this.uutil.save(user);
			
			return "redirect:/admin";
		}

/*----------------------------------------------------------------------------------------------------------*/	
	
	
	
	@GetMapping("/admin-add-service")
	public String addservice(){
		return"admin-add-service";
	}
	
	@PostMapping("/addservices")
	public String add_teacher(@RequestParam("simg") MultipartFile simg,
			@RequestParam("sname") String sname, 
			@RequestParam("sdes") String sdes, HttpSession hs) {

		Services ses=new Services();
		ses.setSname(sname);
		ses.setSdes(sdes);
		ses.setSimg(simg.getOriginalFilename());

		try {
			if (!simg.isEmpty()) {
				boolean uploadfile = fileuploadhelper.uploadFile(simg);
				if (uploadfile) {
					hs.setAttribute("message", "data successfully Inserted");
				}
			} else {
				hs.setAttribute("message", "file is empty");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean s =  adao.addservices(ses);
		if (s) {
			hs.setAttribute("message", "data successfully Inserted...");
		} else {
			hs.setAttribute("message", "data is not inserted");
		}

		return "redirect:/admin-add-service";
	}

	@GetMapping("/admin-view-services")
	public String viewservices(Model m) {
		m.addAttribute("Services", adao.showservices());
		return "admin-view-services";
	}
	
	@GetMapping("/admin-edit-services")
	public String edit(@RequestParam("id") int id, Model m) {
		Services s = adao.getServicesById(id);
		m.addAttribute("services", s);
		return "admin-edit-services";
	}
	
	/*@PostMapping("/updateservices")
	public String updateservices(@ModelAttribute Services s, HttpSession session) {
		adao.addservices(s);
		session.setAttribute("message", "Services Update Sucessfully..");
		return "redirect:/admin-view-services";
	}*/
	
	@PostMapping("/updateservices")
	public String updateservices(@RequestParam("simg") MultipartFile simg,
			@RequestParam("sname") String sname, 
			@RequestParam("sdes") String sdes,
			@RequestParam("id") int s_id, HttpSession hs) {

		Services ses=new Services();
		ses.setId(s_id);
		ses.setSname(sname);
		ses.setSdes(sdes);
		ses.setSimg(simg.getOriginalFilename());

		try {

			if (!simg.isEmpty()) {
				boolean uploadfile = fileuploadhelper.uploadFile(simg);
				if (uploadfile) {
					hs.setAttribute("se", "data successfully Inserted");
				}
			} else {
				hs.setAttribute("se", "file is empty");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean s = adao.addservices(ses);
		if (s) {
			hs.setAttribute("se", "data successfully Inserted...");
		} else {
			hs.setAttribute("se", "data is not inserted");
		}

		List<Services> service1 = adao.showservices();

		hs.setAttribute("services", service1);
		return "redirect:/admin-view-services";
	}

	@GetMapping("/deleteservices/{id}")
	public String deleteser(@PathVariable int id, HttpSession session) {

		adao.deleteservices(id);
		session.setAttribute("ser", "Services Delete Sucessfully..");
		return "redirect:/admin-view-services";
	}
/*----------------------------------------------------------------------------------------------------------*/

	@GetMapping("admin-addflight")
		public String flight(Model m){
		m.addAttribute("cit",adao.ShowCities());
			return"admin-addflight";
		}
	
	@PostMapping("/roundway")
	public String roundway(@RequestParam("fimg") MultipartFile fimg,@RequestParam("fname") String fname,
			@RequestParam("rimg") MultipartFile rimg,@RequestParam("rname") String rname,
			@RequestParam("dastination") String dastination,@RequestParam("source") String source,
			@RequestParam("day") String day,@RequestParam("luggage") String luggage,
			@RequestParam("departuredate") String departuredate,@RequestParam("departuretime") String departuretime,
			@RequestParam("arrivaltime") String arrivaltime,
			@RequestParam("returndate") String returndate,@RequestParam("rdeparturetime") String rdeparturetime,
			@RequestParam("rarrivaltime") String rarrivaltime,@RequestParam("rduration") String rduration,
			@RequestParam("duration") String duration,@RequestParam("price") Double price,@RequestParam("stop") String stop,
			HttpSession hs) {
		
		if(source==dastination){
			hs.setAttribute("flight", "error");
		}
		else {
			
	
		Round_Way_Flights flight= new Round_Way_Flights();
		flight.setFname(fname);
		flight.setRname(rname);
		flight.setDastination(dastination);
		flight.setSource(source);
		flight.setDay(day);
		flight.setLuggage(luggage);
		flight.setDeparturedate(departuredate);
		flight.setDeparturetime(departuretime);
		flight.setArrivaltime(arrivaltime);
		flight.setReturndate(returndate);
		flight.setRdeparturetime(rdeparturetime);
		flight.setRarrivaltime(rarrivaltime);
		flight.setDuration(duration);
		flight.setRduration(rduration);
		flight.setPrice(price);
		flight.setStop(stop);
		flight.setFimg(fimg.getOriginalFilename());
		flight.setRimg(rimg.getOriginalFilename());

		try {
			if (!fimg.isEmpty()) {
				boolean uploadfile = fileuploadhelper.uploadFile(fimg);
				fileuploadhelper.uploadFile(rimg);
				if (uploadfile) {
					hs.setAttribute("flight", "data successfully Inserted");
				}
			} else {
				hs.setAttribute("flight", "file is empty");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean f =  adao.Round_Way_Flights(flight);
		if (f) {
			hs.setAttribute("flight", "data successfully Inserted...");
		} else {
			hs.setAttribute("flight", "data is not inserted");
		}
		}
		return "redirect:/admin-addflight";
	}
	
	@GetMapping("/admin-viewflight")
	public String showRound_Wayflight(Model m) {
		m.addAttribute("flight", adao.showRound_Wayflight());
		return "admin-viewflight";
	}
	
	@GetMapping("/flight")
	public String edits(@RequestParam("id") int id, Model m) {
		m.addAttribute("cit",adao.ShowCities());
		Round_Way_Flights s = adao.getFlightById(id);
		m.addAttribute("flight", s);
		return "admin-edit-flight";
	}
	
	@GetMapping("/deleteflight/{id}")
	public String deleteflight(@PathVariable int id, HttpSession session) {

		adao.deleteflightr(id);
		session.setAttribute("flight", "Flight Delete Sucessfully..");
		return "redirect:/admin-viewflight";
	}
/*----------------------------------------------------------------------------------------------------------*/	
	@GetMapping("admin-addflight2")
	public String flight2(Model m){
	m.addAttribute("cit",adao.ShowCities());
		return"admin-addflight2";
	}
	
	@PostMapping("/oneway")
	public String oneway(@RequestParam("fimg") MultipartFile fimg,@RequestParam("fname") String fname,
			@RequestParam("dastination") String dastination,@RequestParam("source") String source,
			@RequestParam("day") String day,@RequestParam("luggage") String luggage,
			@RequestParam("departuredate") String departuredate,@RequestParam("departuretime") String departuretime,
			@RequestParam("arrivaltime") String arrivaltime,@RequestParam("stop") String stop,
			@RequestParam("duration") String duration,@RequestParam("price") Double price,
			HttpSession hs) {

		One_Way_Flight flight= new One_Way_Flight();
		flight.setFname(fname);
		flight.setDastination(dastination);
		flight.setSource(source);
		flight.setDay(day);
		flight.setLuggage(luggage);
		flight.setDeparturedate(departuredate);
		flight.setDeparturetime(departuretime);
		flight.setArrivaltime(arrivaltime);
		flight.setDuration(duration);
		flight.setPrice(price);
		flight.setStop(stop);
		flight.setFimg(fimg.getOriginalFilename());

		try {
			if (!fimg.isEmpty()) {
				boolean uploadfile = fileuploadhelper.uploadFile(fimg);
				if (uploadfile) {
					hs.setAttribute("flight", "data successfully Inserted");
				}
			} else {
				hs.setAttribute("flight", "file is empty");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean f =  adao.Oneway_Flights(flight);
		if (f) {
			hs.setAttribute("flight", "data successfully Inserted...");
		} else {
			hs.setAttribute("flight", "data is not inserted");
		}

		return "redirect:/admin-addflight2";
	}
	
	@GetMapping("/admin-viewflight2")
	public String showOneway_Flights(Model m) {
		m.addAttribute("flights", adao.showOneway_Flights());
		return "admin-viewflight2";
	}
	
	@GetMapping("/editflight")
	public String editsflight(@RequestParam("id") int id, Model m) {
		m.addAttribute("cit",adao.ShowCities());
		One_Way_Flight s = adao.getFlightsById(id);
		m.addAttribute("flights", s);
		return "admin-edit-flight2";
	}
	
	@GetMapping("/deleteflights")
	public String deleteflights(@RequestParam("id") int id, HttpSession session) {

		adao.deleteflighto(id);
		session.setAttribute("flights", "Flight Delete Sucessfully..");
		return "redirect:/admin-viewflight2";
	}
	
	
/*----------------------------------------------------------------------------------------------------------*/
	
	@GetMapping("admin-addcitie")
	public String cities(){
		return"admin-addcitie";
	}
	
	@PostMapping("/addcities")
	public String addcities(@ModelAttribute Cities c,HttpSession s ) {
		adao.addcities(c);
		s.setAttribute("cit", "Cities Added Sucessfully");
		return"redirect:/admin-addcitie";
	}
	
	@GetMapping("/admin-viewcities")
	public String viewcities(Model m) {
		m.addAttribute("cities", adao.ShowCities());
		return "admin-viewcities";
	}
	
	@GetMapping("/deletecity/{id}")
	public String deletecity(@PathVariable int id, HttpSession session) {

		adao.deletecity(id);
		session.setAttribute("city", "City Delete Sucessfully..");
		return "redirect:/admin-viewcities";
	}
	
/*----------------------------------------------------------------------------------------------------------*/


	
}
