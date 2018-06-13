package com.example.labmacmini01.androidwebservices;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.labmacmini01.androidwebservices.model.Carro;
import com.example.labmacmini01.androidwebservices.model.ItemHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

interface AdapterListener{
    public void celulaClicada(View view, int posicao, Double latitude, Double longitude, Carro carro);
}

public class ActivityListaCarro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista);


        BuscaDadosWebServices busca = new BuscaDadosWebServices(this);
        //BuscaDadosWebServices busca = new BuscaDadosWebServices();
        busca.execute("http://livrowebservices.com.br/rest/carros/tipo/esportivos");

        //RecyclerView listaRecycleView = null;
        //listaRecycleView = (RecyclerView) findViewById(R.id.listaPrincipal);

        //listaRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //listaRecycleView.setItemAnimator(new DefaultItemAnimator());
        //listaRecycleView.setHasFixedSize(true);


        //ActivityListaCarro.Adaptador adapt = new ActivityListaCarro.Adaptador(this, carros);
        //listaRecycleView.setAdapter(adapt);


    }

    private AdapterListener criaListener(){
        return new AdapterListener() {
            @Override
            public void celulaClicada(View view, int position, Double latitude, Double longitude, Carro carro) {


                Bundle parametros = new Bundle();


                //chama a proxima tela
                Context contexto = null;
                contexto = view.getContext();
                Intent intent = new Intent(contexto, LocalizacaoMaps.class);
                //String posicao = Integer.toString(position);
                //Log.d("posicao", posicao);

                parametros.putDouble("latitude", latitude);
                parametros.putDouble("longitude",longitude);

                intent.putExtras(parametros);

                contexto.startActivity(intent);
            }
        };

    }


    static class Adaptador extends RecyclerView.Adapter<ItemHolder>{

        Context contexto = null;
        ArrayList<Carro> lista = null;
        private AlertDialog alerta;
        AdapterListener listener = null;

        Adaptador(Context contexto, ArrayList<Carro> lista, AdapterListener listener){

            this.contexto = contexto;
            this.lista = lista;
            this.listener = listener;
        }

        //METODO CHAMADO N VEZES PARA INFLAR O XML DA CELULA E RETORNAR UM OBJETO DE LAYOUT
        /* Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view. */
        //@NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View celula = LayoutInflater.from(contexto).inflate(R.layout.layout_lista_carro, parent,false );
            ItemHolder item = new ItemHolder(celula);
            return item;
        }
        /*
         * Método que recebe o ViewHolder e a posição da lista.
         * Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
         * É onde a mágica acontece!
         * */
        @Override
        public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
            final Carro carro = lista.get(position);


            Picasso.with(contexto).load(carro.getUrlFoto()).into(holder.getImageCarro());
            holder.getTextoDesc().setText(carro.getDesc());
            holder.getTextoNome().setText(carro.getNome());
            holder.getTextoTipo().setText(carro.getTipo());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.celulaClicada(holder.itemView, position, Double.parseDouble(carro.getLatitude()), Double.parseDouble(carro.getLongitude()), carro);
                }
            });




        /*
            holder.getTextoNome().setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    contexto = view.getContext();
                    Intent intent = new Intent(contexto, ActivityDetails.class);
                    String posicao = Integer.toString(position);
                    Log.d("posicao", posicao);
                    intent.putExtra("Nome", lista.get(position).material);



                    contexto.startActivity(intent);


                }



            });
        */

            holder.getTextoNome().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                    builder.setTitle("Titulo");
                    //define a mensagem
                    builder.setMessage("Apagar " + lista.get(position).getNome());
                    //define um botão como positivo
                    builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //NetworkUtils.Apagar(lista.get(position));
                            Toast.makeText(contexto, lista.get(position).getNome()+" Apagada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //define um botão como negativo.
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    //cria o AlertDialog
                    alerta = builder.create();
                    //Exibe
                    alerta.show();
                    return true;
                }

            });

        }


        //METODO QUE DETERMINA QUANTOS ITENS VAI TER NA LISTA
        /*
         * Método que deverá retornar quantos itens há na lista.
         * Aconselha-se verificar se a lista não está nula como no exemplo,
         * pois ao tentar recuperar a quantidade da lista nula pode gerar
         * um erro em tempo de execução (NullPointerException).
         * */
        @Override
        public int getItemCount() {

            return (lista != null)? lista.size() : 0;
        }


    }






    /**
     * Created by labmacmini01 on 02/05/18.
     */
                                                    /*Parametros/Progesso/Resultado*/
    public class BuscaDadosWebServices extends AsyncTask<String, Void, ArrayList<Carro> >{

    private Context meuContexto;
    private View minhaView;

        public BuscaDadosWebServices(Context contexto){
            meuContexto = contexto;
            //minhaView = view;

        }

        public BuscaDadosWebServices(){

        }




        //Representa o codigo a ser executado antes da thread inicializar
        //Executado na MAIN THREAD
        @Override
        protected void onPreExecute() {
            Log.i("INFO", "ANTES DA THREAD");
        }





        //Metodo chamado durante execução da thread
        //Onde o processamento paralelo é executado
        //Retorna um objeto qualquer pelo POST EXECUTE

        @Override
        protected ArrayList<Carro> doInBackground(String... strings) {

            ArrayList<Carro> vetorCarros = null;

            Log.i("INFO", "DURANTE DA THREAD");
            String jSon = "";
            Gson gson= new Gson();
            RecyclerView lista = null;
            try {
                OkHttpClient cliente = new OkHttpClient() ;
                Request requisicao = new Request.Builder().url(strings[0]).build();
                Response resposta =  cliente.newCall(requisicao).execute();
                jSon = resposta.body().string();

                vetorCarros = gson.fromJson(jSon, new TypeToken<ArrayList<Carro>>(){         }.getType());



            } catch (IOException e) {
                e.printStackTrace();
                int error = 0;
            }


            return vetorCarros;
        }




        //Chamado após a execução da thread
        //Executa na MAIN THREAD
        protected void onPostExecute(ArrayList<Carro> carros) {

            Log.i("INFO", "DEPOIS DA THREAD");


            RecyclerView listaRecycleView = null;
            listaRecycleView = (RecyclerView) findViewById(R.id.listaPrincipal);
            listaRecycleView.setLayoutManager(new LinearLayoutManager(meuContexto));
            listaRecycleView.setItemAnimator(new DefaultItemAnimator());
            listaRecycleView.setHasFixedSize(true);


            ActivityListaCarro.Adaptador adapt = new ActivityListaCarro.Adaptador(meuContexto, carros, criaListener() );
            listaRecycleView.setAdapter(adapt);


        }


    }
}
