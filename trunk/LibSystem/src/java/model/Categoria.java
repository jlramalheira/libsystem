/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joao
 */
public enum Categoria {
    
      LIVRO ("Livro",true),
      PERIODICO ("Periódico",false), 
      ENCICLOPEDIA ("Enciclopédia",false), 
      MIDIA ("Mídia",true), 
      DICIONARIO ("Dicionário",true), 
      MAPA ("Mapa",false);
      
      private final boolean emprestavel;
      private final String valor;
      
      Categoria (String valor, boolean emprestavel){
          this.valor = valor;
          this.emprestavel = emprestavel;
      }
      
      public boolean emprestavel(){
          return this.emprestavel;
      }
      
      public String valor(){
          return this.valor;
      }
}
