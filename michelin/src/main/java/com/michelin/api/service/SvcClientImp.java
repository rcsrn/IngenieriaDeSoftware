package com.michelin.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.dto.LoginDto;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.entity.Client;
import com.michelin.api.entity.Product;
import com.michelin.api.repository.RepoClient;
import com.michelin.api.repository.RepoOrder;
import com.michelin.api.repository.RepoProduct;
import com.michelin.api.repository.RepoSale;
import com.michelin.exception.ApiException;

@Service
public class SvcClientImp implements SvcClient {
    
    @Autowired
    RepoClient repo;
    
    @Autowired
    RepoProduct repoProduct;

    @Autowired
    RepoClient repoClient;

    @Autowired
    RepoSale repoSale;

    @Autowired
    RepoOrder repoOrder;

    @Autowired
    JavaMailSender emailSender;
    
    @Override
    public ApiResponse registerClient(ClientDto in) throws MessagingException {
        
        Client client = repo.findByEmail(in.getEmail());

        if (client != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El correo ya esta registrado");
        }

        String password = generateNewPassword(10);
        try {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(in.getDateOfBirth());
        repo.createClient(in.getName(), in.getEmail(), password, date);
        } catch (ParseException pe) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto");
        }
        sendPasswordEmail(in.getEmail(), password);
        return new ApiResponse("registro exitoso, su contraseña se ha enviado su correo.");
    }

    private String generateNewPassword(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
    
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    private void sendPasswordEmail(String email, String password) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject("Michelin: Nueva contraseña generada!");
        helper.setText("Su nueva contraseña es: " + password);

        emailSender.send(message);
    }



    @Override
    public ApiResponse updatePassword(PasswordDto in, Integer client_id) throws MessagingException {
        Client client = repo.findByClientId(client_id);
        if (client == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El cliente no existe");
        }
        repo.updatePassword(in.getNewPassword(), client_id);

        String email = client.getEmail();
        sendPasswordEmail(email, in.getNewPassword());

        return new ApiResponse("Contraseña actualizada");

    }

    public List<Product> getAllProducts() {
        return repoProduct.getAllProducts();
    }

    public ApiResponse createOrder(Integer product_id, Integer client_id) {
        Client client = repoClient.findByClientId(client_id);

        if (client == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El cliente no existe");  
        }

        Product product = repoProduct.findProductById(product_id);

        if (product == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El producto no esta disponible"); 
        }      

       Integer sale_id =  repoSale.createSale(product.getPrice(), 3, new Date());
       System.out.println(sale_id);
       
       repoOrder.createSale(4, 5, client_id);

        return new ApiResponse("pedido en proceso");   
    }

    @Override
    public ApiResponse login(LoginDto in) {
        Client client = repo.findByEmail(in.getEmail());
        if (client == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "email incorrecto");
        }

        System.out.println(client.getPassword().equals(in.getPassword()));

        if (!client.getPassword().equals(in.getPassword())) {
            throw new ApiException(HttpStatus.NOT_FOUND, "contraseña incorrecta");
        }

        return new ApiResponse("exito");
    }
}
