/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso
 */
public class NewsManagerTest {
    private NewsManager instance;
    private News not;
    
    public NewsManagerTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         instance = NewsManager.getInstance();
        not =new News();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class NewsManager.
     */
    @Test
    public void testGetInstance() {
        NewsManager result = NewsManager.getInstance();
         assertNotNull(result);
    }

    /**
     * Test of insertNews method, of class NewsManager.
     */
    @Test
    public void testInsertNewsok(){
        not.setId(1);
        not.setTitle("Prova di avviso");
        not.setDescription("Descrizione avviso");
        try{
            instance.insertNews(not);
            assertTrue(true);
        }catch(Exception x){
            fail(" non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testInsertIdMinNews(){
         not.setId(-1);
         not.setTitle("Prova di avviso");
         not.setDescription("Descrizione avviso");
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testInsertIdMaxNews(){
        not.setId(1000000);
        not.setTitle("Prova di avviso");
         not.setDescription("Descrizione avviso");   
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertTitleMinNews(){
        not.setId(1);          
        not.setTitle("");
        not.setDescription("Descrizione avviso"); 
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertTitleMaxNews(){
        not.setId(1);          
        not.setTitle("qwertyuiopqwertyuiopqwertsadfasffgdgdfgdfgdfyuiopqwertyuiopqwertyuiopqwertyuiop");
        not.setDescription("Descrizione avviso"); 
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertDescriptionMinNews(){
        not.setId(1);          
        not.setTitle("Titolo avviso");
        not.setDescription("");
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }

    /**
     * Test of getNewsById method, of class NewsManager.
     */
    @Test
    public void testGetNewsByIdok(){
        int number = 1;
        try{
            instance.getNewsById(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    @Test
    public void testGetNewsByIdMin(){
        int number = -1;
        try{
            instance.getNewsById(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testGetNewsByIdMax(){
        int number = 2000;
        try{
            instance.getNewsById(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of deleteNews method, of class NewsManager.
     */
    @Test
    public void testDeleteNewsok(){
       int id=2;      
        try{
            instance.deleteNews(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
     @Test
    public void testDeleteNewsIdMin(){
        int id = -1;
        try{
            instance.deleteNews(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    
    
    @Test
    public void testDeleteNewsIdMax(){
        int id = 20000;
        try{
            instance.deleteNews(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of update_news method, of class NewsManager.
     */
    @Test
    public void testUpdate_newsok(){
        int Number = 1;
        not.setTitle("Prova");
        not.setDescription("Prova descrizione");
        try{
            instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsTitleMin(){
        int Number = 1;
        not.setTitle("");
        not.setDescription("Prova descrizione");
        try{
             instance.update_news(Number,not);
             fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdate_newsTitleMax(){
        int Number = 1;
        not.setTitle("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        not.setDescription("Prova descrizione");
        try{
            instance.update_news(Number,not);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdate_newsIdMin(){
        int Number = -5;
        not.setTitle("qwqwertyuiopqwertyuiopqwertyuiop");
        not.setDescription("Prova descrizione");
        not.setId(Number);
        try{
            instance.update_news(Number,not);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdate_newsIdMax(){
        int Number = 2000000;
        not.setDescription("Prova descrizione");
        not.setId(Number);
        not.setTitle("qwqwertyuiopqwertyuiopqwertyuiop");
        try{
            instance.update_news(Number,not);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdate_newsDescriptionMin(){
        int Number = 1;
        not.setTitle("Prova");
        not.setDescription(null);
        try{
             instance.update_news(Number,not);
             fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }   

    @Test
    public void testGetAllNewsOk(){
        try{
            ArrayList<News> result=instance.getAllNews();
            assertNotNull(not);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
}
