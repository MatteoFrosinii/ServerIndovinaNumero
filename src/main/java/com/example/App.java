package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
        try {
            ServerSocket server = new ServerSocket(2750);
            Socket s = server.accept();
            server.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            out.writeBytes(null);
            
            out.writeBytes("Scegli la difficolta:"+'\n');
            out.writeBytes("1 - Da 1 a 10"+'\n');
            out.writeBytes("2 - Da 1 a 50"+'\n');
            out.writeBytes("3 - Da 1 a 100"+'\n');
            String stringaRicevuta = in.readLine();

            int numeroDaIndovinare = 0;
            switch(Integer.parseInt(stringaRicevuta)){
                case 1: numeroDaIndovinare = (int) (Math.random()*10+1); break;
                case 2: numeroDaIndovinare = (int) (Math.random()*50+1); break;
                case 3: numeroDaIndovinare = (int) (Math.random()*100+1); break;
            }

            int numeroIndovinato,totTentativi = 0;
            out.writeBytes("Prova ad indovinare il numero: "+'\n');

            do {
                stringaRicevuta = in.readLine();
                numeroIndovinato = Integer.parseInt(stringaRicevuta);
                if(numeroIndovinato == numeroDaIndovinare){
                    totTentativi++;
                    break;
                } else {
                    totTentativi++;
                    out.writeBytes("Hai sbagliato numero: Riprova"+'\n');
                }
            } while (true);

            out.writeBytes("Hai indovinato in il numero in " + totTentativi + " tentativi");
            s.close();
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
