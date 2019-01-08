package przychodnia;
// Generated 2019-01-04 14:24:14 by Hibernate Tools 4.3.1



/**
 * PacjentkartaId generated by hbm2java
 */
public class PacjentkartaId  implements java.io.Serializable {


     private int idPacjent;
     private int idKarta;

    public PacjentkartaId() {
    }

    public PacjentkartaId(int idPacjent, int idKarta) {
       this.idPacjent = idPacjent;
       this.idKarta = idKarta;
    }
   
    public int getIdPacjent() {
        return this.idPacjent;
    }
    
    public void setIdPacjent(int idPacjent) {
        this.idPacjent = idPacjent;
    }
    public int getIdKarta() {
        return this.idKarta;
    }
    
    public void setIdKarta(int idKarta) {
        this.idKarta = idKarta;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PacjentkartaId) ) return false;
		 PacjentkartaId castOther = ( PacjentkartaId ) other; 
         
		 return (this.getIdPacjent()==castOther.getIdPacjent())
 && (this.getIdKarta()==castOther.getIdKarta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdPacjent();
         result = 37 * result + this.getIdKarta();
         return result;
   }   


}


