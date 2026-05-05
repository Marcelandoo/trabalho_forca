package com.example.forca_integrado;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class Telajogo extends AppCompatActivity implements View.OnClickListener {
    private ImageView imagem;
    private ArrayList<Integer> lista_imagens, lista_id_buttons;
    private ArrayList<String> lista_palavras;
    private int indice_lista_imagens, conta_acerto, conta_erro;
    private Button b1;
    private TextView texto, txAcerto, txErro;
    private String palavra;
    private char[] estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telajogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imagem = findViewById(R.id.imageView2);
        txAcerto = findViewById(R.id.textView5);
        txErro = findViewById(R.id.textView7);
        indice_lista_imagens = 0;
        conta_acerto = 0;
        conta_erro= 0;
        lista_imagens = new ArrayList<Integer>();
        lista_imagens.add (R.drawable.forca_1_9);
        lista_imagens.add (R.drawable.forca_2_9);
        lista_imagens.add (R.drawable.forca_3_9);
        lista_imagens.add (R.drawable.forca_4_9);
        lista_imagens.add (R.drawable.forca_5_9);
        lista_imagens.add (R.drawable.forca_6_9);
        lista_imagens.add (R.drawable.forca_7_9);
        lista_imagens.add (R.drawable.forca_9_9);
        lista_imagens.add (R.drawable.forca_10_9);
        lista_imagens.add (R.drawable.forca_111_9);

        texto = findViewById(R.id.textView3);

        lista_id_buttons = new ArrayList<Integer>();
        lista_id_buttons.add(R.id.id1);
        lista_id_buttons.add(R.id.id2);
        lista_id_buttons.add(R.id.id3);
        lista_id_buttons.add(R.id.id4);
        lista_id_buttons.add(R.id.id5);
        lista_id_buttons.add(R.id.id6);
        lista_id_buttons.add(R.id.id7);
        lista_id_buttons.add(R.id.id8);
        lista_id_buttons.add(R.id.id9);
        lista_id_buttons.add(R.id.id10);
        lista_id_buttons.add(R.id.id11);
        lista_id_buttons.add(R.id.id12);
        lista_id_buttons.add(R.id.id13);
        lista_id_buttons.add(R.id.id14);
        lista_id_buttons.add(R.id.id15);
        lista_id_buttons.add(R.id.id16);
        lista_id_buttons.add(R.id.id17);
        lista_id_buttons.add(R.id.id18);
        lista_id_buttons.add(R.id.id19);
        lista_id_buttons.add(R.id.id20);
        lista_id_buttons.add(R.id.id21);
        lista_id_buttons.add(R.id.id22);
        lista_id_buttons.add(R.id.id23);
        lista_id_buttons.add(R.id.id24);
        lista_id_buttons.add(R.id.id25);
        lista_id_buttons.add(R.id.id26);

        b1 = findViewById(R.id.id1);
        b1.setOnClickListener(this);

        lista_palavras = new ArrayList<String>();
        lista_palavras.add("TEXAS");
        lista_palavras.add("ANOITECER");
        lista_palavras.add("QUIMERA");
        lista_palavras.add("ANDROIDE");
        lista_palavras.add("ANATOMIA");
        lista_palavras.add("VELUDO");
        lista_palavras.add("SILENCIO");
        lista_palavras.add("PIANISTA");
        lista_palavras.add("TITANIO");
        lista_palavras.add("SACRAMENTO");

        for(int j = 0; j < lista_id_buttons.size(); j++){
            Button b = findViewById(lista_id_buttons.get(j));
            b.setOnClickListener(this);
        }
        iniciar_jogo();

    }

    public void checa_terminou(){
        boolean verifica = false;
        for(int i = 0; i < estado.length; i++){
            if(estado[i] == '_'){
                verifica = true;
            }
        }
        if(!verifica){
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Você venceu!");
            caixa.setMessage("Deseja jogar novamente?");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    iniciar_jogo();
                }
            });
            caixa.show();
        }
        if(conta_erro >= lista_imagens.size()){
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Você perdeu!");
            caixa.setMessage("Deseja jogar novamente?");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    iniciar_jogo();
                }
            });
            caixa.show();
        }
    }

    public  void iniciar_jogo(){
        imagem.setImageResource(R.drawable.forca_0_9);
        indice_lista_imagens = 0;
        palavra = sorteia_palavra();
        estado = new char[palavra.length()];
        for(int i = 0; i < estado.length; i++){
            estado[i] = '_';
        }
        conta_acerto = 0;
        conta_erro = 0;
        txAcerto.setText(Integer.toString(conta_acerto));
        txErro.setText(Integer.toString(conta_erro)+"/"+Integer.toString(lista_imagens.size()));
        for(int j = 0; j < lista_id_buttons.size(); j++){
            Button b = findViewById(lista_id_buttons.get(j));
            b.setEnabled(true);
        }
        atualiza_texto();
    }
    public  void verificar_letra(char c){
        boolean status = false;
        for(int i = 0; i<palavra.length(); i++){
            if(palavra.charAt(i) == c){
                status = true;
                estado[i] = c;
            }
        }
        if(!status){
            atualizar_forca();
            conta_erro ++;
            txErro.setText(Integer.toString(conta_erro)+"/"+Integer.toString(lista_imagens.size()));
        }
        else{
            atualiza_texto();
            conta_acerto ++;
            txAcerto.setText(Integer.toString(conta_acerto));
        }
        checa_terminou();
    }

    public void atualiza_texto(){
        String temporaria = new String();
        temporaria = " ";
        for( int i = 0; i < estado.length; i++){
            temporaria += estado[i] + " ";
        }
        texto.setText(temporaria);
    }
    public String sorteia_palavra(){
        String retorno = new String();
        Collections.shuffle(lista_palavras);
        retorno = lista_palavras.get(0);
        return retorno;
    }

    public void atualizar_forca(){
        imagem.setImageResource(lista_imagens.get(indice_lista_imagens));
        indice_lista_imagens++;
    }
    @Override
    public void onClick(View v){
        //casting
        Button b = (Button) v;
        verificar_letra(b.getText().toString().charAt(0));
        b.setEnabled(false);


    }
}