package com.example.lighton;

import static org.junit.Assert.*;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    Login prueba = new Login();
    @Before
    public void antesDeCadaPrueba(){
        System.out.println("Antes de cada prueba");
    }
    @Test
    public void registro() {
        //Dado que
        String name = "Lina Mendez";
        String email = "Lina@gmail.com";
        String password = "12345678";
        String repeat = "12345678";
        //Cuando ejecutamos
        if (name.equals("Lina Mendez") && email.equals("Lina@gmail.com") && password.equals("12345678") && repeat.equals(password))
        {
            System.out.println("inicio de sesion correcta");

        }
        else
        {
            if(password != repeat)
            {
                System.out.println("Contraseña incorrecta");
            }
        }
        //Entonces
        assertEquals(password,repeat);

    }

}