package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args ){
        try {
            ServerSocket server = new ServerSocket(2750);
            Socket s = server.accept();
            server.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
                        
            String stringaRicevuta = in.readLine();

            int numeroDaIndovinare = 0;
            switch(Integer.parseInt(stringaRicevuta)){
                case 1: numeroDaIndovinare = (int) (Math.random()*10+1); break;
                case 2: numeroDaIndovinare = (int) (Math.random()*50+1); break;
                case 3: numeroDaIndovinare = (int) (Math.random()*100+1); break;
            }

            int numeroIndovinato,totTentativi = 0;
            System.out.println(numeroDaIndovinare);

            do {
                stringaRicevuta = in.readLine();
                numeroIndovinato = Integer.parseInt(stringaRicevuta);
                totTentativi++;
                if(numeroIndovinato == numeroDaIndovinare){
                    out.writeBytes("I"+'\n');
                    out.writeBytes(String.valueOf(totTentativi));
                    break;
                } else if(numeroIndovinato > numeroDaIndovinare) {
                    out.writeBytes("m"+'\n');
                } else {
                    out.writeBytes("M"+'\n');
                }
            } while (true);

            s.close();
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
