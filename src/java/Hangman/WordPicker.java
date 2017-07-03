package Hangman;

import java.util.Random;
import javax.persistence.*;

public class WordPicker {
    
    public static String getWord() {
  
        try {
        EntityManager em = Data.DBUtil.getEM();
        Query query =  em.createQuery("select count(w) from Word w ");
        long count = (long) query.getSingleResult(); 
        Random generator = new Random();
        int randkey = generator.nextInt((int)count)+1;
        Query query2  =  em.createQuery("select a.word from Word a where a.id=?1");
        query2.setParameter(1,randkey);
        String word = (String) query2.getSingleResult();
        em.close();
        System.out.println("WordPicker. word="+word);
        return word;
        } catch (Exception e){
            System.out.println("Exception in WordPicker:getWord "+e.getMessage());
            e.printStackTrace();
            return "computer";
        }
    }
    
}