package Data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Game history contains user name, date-time, word, and game outcome: win, lose or abandon
 * @author austin hasemeyer
 * @version 1.0
 */
@Entity
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date; 
    private String userName;
    private String status; // win, lose, or abandon
    
    public History() {
        id=null;
        word="";
        userName="unknown";
        status="abandon";
        date = new Date(); // set to current date
    }
    
    public String getWord() { return word;}
    public void setWord(String word) { this.word = word; }
    
    public String getUserName() { return userName; }
    public void setUserName(String n) { userName=n; }
    
    public String getStatus() { return status; }
    public void winGame(){ status = "win";}
    public void loseGame() { status="lose";}
    public void abandonGame() { status="abandon";}
    
    public Date getDate() { return date; }
    public void setDate(Date value) { date=value; }

    public Long getId() {
        return id;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst438.History[ id=" + id + " ]";
    }
    
}

