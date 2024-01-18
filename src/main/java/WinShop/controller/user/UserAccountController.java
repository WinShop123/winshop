package WinShop.controller.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import WinShop.dto.CartDto;
import WinShop.entity.Account;
import WinShop.entity.Cart;
import WinShop.service.IAccountService;
import WinShop.service.ICartService;
import WinShop.service.IRoleService;

@Controller
public class UserAccountController extends UserBaseController {
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private ICartService cartService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView Login() {
		mv.setViewName("all/login");
		mv.addObject("loginaccount", new Account());
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView Login(@ModelAttribute("loginaccount") Account account, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("all/login");
		try {
			String password = account.getPassword();
			Account check = accountService.getAccountByUsername(account.getUsername().trim());
			if(check != null) {
				if(BCrypt.checkpw(password, check.getPassword())) {
					Account loginedAccount = (Account) session.getAttribute("loginsession");
					if(loginedAccount != null) {
						session.removeAttribute("loginsession");
						session.removeAttribute("rolesession");
						session.removeAttribute("cart");
					}
					session.setAttribute("loginsession", check);
					session.setAttribute("rolesession", roleService.getRoleByAccountId(check.getId()));
					List<Cart> list = cartService.getCartsDataByUsername(check.getUsername());
					HashMap<Long, CartDto> cart = new HashMap<Long, CartDto>();
					if(!list.isEmpty()) {
						cart = cartService.createCartSession(list, check.getUsername());
						session.setAttribute("cart", cart);
						session.setAttribute("totalQuantity", cartService.totalQuantity(cart));
						session.setAttribute("totalPrice", cartService.totalPrice(cart));
					}
					mv.setViewName("redirect:/");
					return mv;
				}
				mv.addObject("iscorrect", false);
				return mv;
			}
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			mv.addObject("isexist", false);
			return mv;
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView Signup() {
		mv.setViewName("all/signup");
		mv.addObject("newaccount", new Account());
		return mv;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView Signup(@ModelAttribute("newaccount") Account account) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("all/signup");
		try {
			int check = 0;
			List<Account> list = accountService.getAccountsData();
			if(list.isEmpty()) {
				check = 1;
			}
			else {
				for(Account item : list) {
					check = 1;
					if(item.getUsername().equals(account.getUsername())) {
						check = 0;
						break;
					}
				}
			}
			if(check == 1) {
				int addAccount = accountService.addAccount(account);
				Account created = accountService.getAccountByUsername(account.getUsername().trim());
				roleService.setRole(created.getId(), "user");
				if(addAccount > 0) {	
					mv.addObject("issuccess", true);
				}
			}
			else {
				mv.addObject("isexist", true);
			}
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", e.getMessage());
			return mv;
		}
	}
	
	@RequestMapping("/logout")
	public String Logout(HttpSession session, HttpServletRequest request) {
		mv.setViewName("/logout");
		session.removeAttribute("loginsession");
		session.removeAttribute("rolesession");
		session.removeAttribute("cart");
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/changepass", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		mv.setViewName("all/password/change");
		mv.addObject("changepassword", new Account());
		return mv;
	}
	
	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute("changepassword") Account account, 
			@RequestParam("newpassword") String newpassword, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("all/password/change");
		Account logined = (Account)session.getAttribute("loginsession");
		try {
			if(BCrypt.checkpw(account.getPassword(), accountService.getAccountByUsername(logined.getUsername()).getPassword())) {
				accountService.setNewPassword(BCrypt.hashpw(newpassword, BCrypt.gensalt(12)), logined.getUsername());
				mv.addObject("issuccess", true);
				return mv;
			}
			else {
				mv.addObject("iscorrect", false);
				return mv;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
//	@RequestMapping(value = "/forgotpass", method = RequestMethod.GET)
//	public ModelAndView forgotPassword() {
//		mv.setViewName("all/password/forgot");
//		mv.addObject("forgotaccount", new Account());
//		return mv;
//	}
//	
//	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
//	public ModelAndView forgotPassword(@ModelAttribute("forgotaccount") Account account, HttpSession session) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("all/password/forgot");
//		try {
//			Account check = accountService.getAccountByUsername(account.getUsername());
//			String code = generateCode();
//			verifyCode verifyCode = new VerifyCode();
//			verifyCode.setCode(code);
//			verifyCode.setUsername(account.getUsername());
//			session.setAttribute("code", verifyCode);
//			sendMail("Mã xác nhận của bạn", account.getUsername().trim(), code);
//			mv.setViewName("redirect:/forgotpass/getcode");
//			return mv;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			mv.addObject("isexist", false);
//			return mv;
//		}
//	}
//	
//	@RequestMapping(value = "/forgotpass/getcode", method = RequestMethod.GET)
//	public ModelAndView getCode() {
//		mv.setViewName("all/password/getcode");
//		return mv;
//	}
//	
//	@RequestMapping(value = "/forgotpass/getcode", method = RequestMethod.POST)
//	public ModelAndView getCode(@RequestParam("code") String code, HttpSession session) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("all/password/getcode");
//		try {
//			VerifyCode verifyCode = (VerifyCode)session.getAttribute("code");
//			if(code.equals(verifyCode.getCode())) {
//				String password = generateCode();
//				accountService.setNewPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)), verifyCode.getUsername());
//				sendMail("Mật khẩu của bạn là", verifyCode.getUsername(), password);
//				mv.addObject("issuccess", true);
//				return mv;
//			}
//			else {
//				mv.addObject("iscorrect", false);
//				return mv;
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return mv;
//		}
//	}
//	
//	public String generateCode() {
//		String [] characters = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9","a", "b", "c", "d", "e", "f", "g",
//				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
//				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", 
//				"T", "U", "V", "W", "X", "Y", "Z"};
//		String code = "";
//		for(int i=0; i<= 5; i++) {
//			code += characters[(int)(Math.random() * characters.length)];
//		}
//		return code;
//	}
//	
//	public void sendMail(String subject, String toEmail, String code) {
//		final String username = "hiroshop76@gmail.com";
//        final String password = "oxbbzbppguvnitui";
//
//        Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true"); //TLS
//        
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        
//        try {
//
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("hiroshop76@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse(toEmail)
//            );
//            message.setSubject(subject, "utf-8");
//            message.setText(code + "\n\n Please do not spam my email!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//	}
}
