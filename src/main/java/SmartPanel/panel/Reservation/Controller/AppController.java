package SmartPanel.panel.Reservation.Controller;

import SmartPanel.panel.Reservation.SmartPanel.Model.*;
import SmartPanel.panel.Reservation.SmartPanel.Repositories.ContactusRepository;
import SmartPanel.panel.Reservation.SmartPanel.Repositories.PanelRepository;
import SmartPanel.panel.Reservation.SmartPanel.Repositories.RoleRepository;
import SmartPanel.panel.Reservation.SmartPanel.Service.PanelService;
import SmartPanel.panel.Reservation.SmartPanel.Repositories.UserRepository;
import SmartPanel.panel.Reservation.fileUtil.fileDownload;
import SmartPanel.panel.Reservation.fileUtil.uploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller

public class AppController {

    @Autowired private ContactusRepository contactRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PanelService service;
    @Autowired
    private PanelRepository panelRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RoleRepository roles;

    @GetMapping("/")
    public String home() {
        return "Home";
    }
    @RequestMapping("/")
    public String viewHomePage(@Validated HttpSession session,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        session.setAttribute("email", email);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        PanelUser user = userRepository.findByUsername(username);


        // Add the authenticated user to the model
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        return "Home";
    }

    @GetMapping("/Register")
    public String ShowSignupForm(Model model){
        model.addAttribute("user",new PanelUser());
        return "SignupH";
    }
    @PostMapping("/process_Register")
    public String processReg(PanelUser user){
        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encoded = encoder.encode(user.getPassword());
            user.setPassword(encoded);
            PanelRoles customer = new PanelRoles("CUSTOMER");
            user.getRoles().add(customer);
            userRepository.save(user);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Welcome to Smart Panel!");
            message.setText("Dear " + user.getUsername() + ",\n\nThank you for registering with us. We look forward to serving you.\n\nBest regards,\nThe SmartPanel Team");
            mailSender.send(message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }
    @RequestMapping  ("/Contact")
    public String NewAllContacts( Model model){
        Contactus Cont = new Contactus();
        model.addAttribute("Cont", Cont);
        return "Contactus";
    }
    @RequestMapping  ("/Distributor")
    public String Distributor( Model model){
        Contactus Cont = new Contactus();
        model.addAttribute("Cont", Cont);
        return "Distributors";
    }
    @RequestMapping  ("/panelCost")
    public String Calculator( Model model){
        return "panelCost";
    }

    @RequestMapping(value = "/SaveContact",method = RequestMethod.POST )
    public String SaveNewContact(@RequestBody @Validated @ModelAttribute("Cont") Contactus contact, RedirectAttributes redirectAttributes , PanelUser user){
        try {
            contactRepo.save(contact);
            redirectAttributes.addFlashAttribute("message","Data has been saved successfully");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Welcome to our productSpec!");
            message.setText("Dear " + user.getUsername() + ",\n\nThank you for registering with us. We look forward to serving you.\n\nBest regards,\nThe SmartPanel Team");
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:/list_user";
        }

    }
    @RequestMapping(value = "/Save",method = RequestMethod.POST )
    public String SavePanel(@Validated @ModelAttribute("productSpec") Contactus contact, RedirectAttributes redirectAttributes , ProductSpec productSpec,PanelUser user,Model model ){
        try {
            model.addAttribute("productSpec");
            service.save(productSpec);
            redirectAttributes.addFlashAttribute("message","Panel has been saved successfully");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Welcome to our productSpec!");
            message.setText("Dear " + user.getUsername() + ",\n\nThank you for registering Panel with us. We look forward to serving you.\n\nBest regards,\nThe SmartPanel Team");
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:/Pannel";
        }

    }

    @RequestMapping("/AboutUs")
    public String AboutusHome(){
        return "Aboutus";
    }

    @RequestMapping(value = "/list_users",method = RequestMethod.GET)
    public String ViewList(Model model, HttpServletRequest request){
        List<PanelUser> listUsers = userRepository.findAll();
        String header = request.getHeader("Authorization");
        String path = request.getRequestURI();
        model.addAttribute("listUsers",listUsers);
        return "list_users";
    }

    @RequestMapping  ("/AllPanels")
    public String ListProducts( Model model, @Param("keyword")String keyword){

        List<ProductSpec> productSpec = service.list(keyword);
        model.addAttribute("productSpec", productSpec);
        model.addAttribute("keyword", keyword);
        return  ListBYPage(model,1);
    }

    @RequestMapping ("/Admin")
    public String AdminDash (Model model){
        return "Admin";
    }
    @RequestMapping ("/Images")
    public String ima (Model model){
        return "images";
    }
    @GetMapping("/page/{pageNumber}")
    public String ListBYPage(Model model,@PathVariable("pageNumber") int currentPage){

        Page<ProductSpec> page = service.listAll(currentPage);

        Long  totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<ProductSpec> listProductSpec = page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listProductSpec", listProductSpec);
        return "Pannel";
    }



    @RequestMapping  ("/new")
    public String NewAllProduct( Model model){
        ProductSpec productSpec = new ProductSpec();
        model.addAttribute("productSpec", productSpec);
        return "new_Panel";
    }
    @RequestMapping  ("/Panels")
    public String Product( Model model){

        ProductSpec productSpec = new ProductSpec();
        model.addAttribute("productSpec", productSpec);
        return "Products";
    }
    @RequestMapping(value = "/Save",method = RequestMethod.POST )
    public String SaveProduct(@RequestBody @Validated @ModelAttribute("productSpec") ProductSpec product, RedirectAttributes redirectAttributes , PanelUser user){
        try {
            service.save(product);
            redirectAttributes.addFlashAttribute("message","Data has been saved successfully");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Welcome to our productSpec!");
            message.setText("Dear " + user.getUsername() + ",\n\nThank you for registering with us. We look forward to serving you.\n\nBest regards,\nThe SmartPanel Team");
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:/Pannel";
        }

    }

    @RequestMapping ("/edit/{id}")
    public ModelAndView showEditHotel(@PathVariable(name ="id") Long id){
        ModelAndView mav = new ModelAndView("edit_Product");
        ProductSpec panelSpec = service.get(id);
        mav.addObject("panelSpec", panelSpec);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id")Long id){
        service.delete(id);
        return "redirect:/list_users";
    }
    @RequestMapping("/grantRole")
    public String grantRoleToUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        PanelUser user = userRepository.findById(userId).orElse(null);
        PanelRoles role = roles.findById(roleId).orElse(null);

        if (user == null || role == null) {
            return "Invalid user or role ID";
        }
        user.addRole(role);
        userRepository.save(user);

        return "Role granted successfully to the user";
    }

    @PostMapping("/fileUpload")
    //@RolesAllowed("ROLE_ADMIN")
    public String FileUpload(@RequestParam("file") @RequestPart MultipartFile multipartFile, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String fileCode = uploadUtil.saveFile(fileName,multipartFile);
        fileUpload fileUpload = new fileUpload();
        model.addAttribute("fileUpload", fileUpload);
        model.addAttribute("fileName", multipartFile.getOriginalFilename());
        model.addAttribute("fileSize", multipartFile.getSize());
        fileUpload.setFilename(fileName);
        fileUpload.setSize(size);
        fileUpload.setDownloadUri("/downloadFile/"+fileCode);
        return "fileUpload";
    }

    @GetMapping("/downloadFile")
   // @RolesAllowed({"ROLE_CUSTOMER", "ROLE_DISTRIBUTOR","ROLE_ADMIN"})
    public
    ResponseEntity<Resource> download(@RequestParam("filecode") String fileCode){
        fileDownload download = new fileDownload();

        Resource resource = null;
        try{
            resource = download.getFileResource(fileCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }if (resource == null){
            return new ResponseEntity("File not found",HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\""+ resource.getFilename()+"\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,headerValue).body(resource);
    }


}
