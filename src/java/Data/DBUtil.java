package Data;

import javax.persistence.*;
/**
 *
 * @author david
 */
public class DBUtil {
    
    private static EntityManagerFactory emf = null;
    
    
    public static EntityManager getEM() {
        try {
        if (emf==null){
            getEMF();
        } 
        return emf.createEntityManager();
        } catch (Exception e){
            System.out.println("Exception in getEM "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    private synchronized static void  getEMF() {
        try {
        if (emf==null){
            emf = Persistence.createEntityManagerFactory("cst438HangmanPU");
        } 
        } catch (Exception e){
            System.out.println("Excepton in getEMF "+e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Check game history to see if this user has used this word
     * @param word 
     * @param userName
     * @return true the word has been used by this user in a game, otherwise false
     */
    public static boolean checkHistoryForWord(String word, String userName){
        try {
        EntityManager em = getEM();
        Query query = em.createQuery("select count(*) from History h where h.userName = ?1 and h.word = ?2");
        query.setParameter(1, userName);
        query.setParameter(2, word);                
        long count = (long)(query.getSingleResult());
        em.close();
        if (count==0) return false;
        return true;
        } catch (Exception e){
            System.out.println("Exception in checkHistoryForWord "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    
    /*
    public static History createHistory(String word, String userName){
        try {
        System.out.println("createHistory start.");
        EntityManager em = getEM();
        em.getTransaction().begin();
        History h = new History();
        h.setWord(word);
        h.setUserName(userName);
        em.persist(h);
        em.getTransaction().commit();
        em.close();
        System.out.println("createHistory ended.");
        return h;
        } catch (Exception e){
            System.out.println("Exception in createHistory "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public static void updateHistory(History h) {
        try {
        System.out.println("updateHistory start.");
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.merge(h);
        em.getTransaction().commit();
        em.close();
        System.out.println("updateHistory ended.");
        } catch (Exception e){
            System.out.println("Exception in updateHistory "+e.getMessage());
            e.printStackTrace();
        }
    }
*/
}