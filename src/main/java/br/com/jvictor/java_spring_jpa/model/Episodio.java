package br.com.jvictor.java_spring_jpa.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

   public Episodio (Integer numeroTemporada, DadosEpisodio dadosEpisodio){
       this.temporada = numeroTemporada;
       this.titulo = dadosEpisodio.titulo();
       this.numeroEpisodio = dadosEpisodio.numero();

       try {
           this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
       } catch (NumberFormatException ex) {
           this.avaliacao = 0.0;
       }

       try {
           this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
       } catch (DateTimeParseException ex) {
           this.dataLancamento = null;
       }
   }
}
