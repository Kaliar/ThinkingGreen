package mx.itesm.thinkinggreen.Models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class getAdviceTextInfo {


    private void getWebsite() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder= new StringBuilder();

                try {
                    Document doc= Jsoup.connect("https://laecocosmopolita.com/2017/02/14/los-mejores-consejos-para-una-vida-zero-waste-ecobloggers/").get();
                    String title=doc.title();
                    String links=doc.select("meta[name=description]").get(0).attr("content");
                    builder.append(title).append("\n");
                    builder.append(links);

                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");

                }

                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //etContenido.setText(builder.toString());
                    }
                });*/

            }
        }).start();



    }

}
