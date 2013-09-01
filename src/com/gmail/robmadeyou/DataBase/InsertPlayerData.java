package com.gmail.robmadeyou.DataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Mrgadgetz
 * Date: 8/30/13
 * Time: 9:47 PM
 */
public class InsertPlayerData {

      public InsertPlayerData(int id, String name, Long mobile, String email){

          Configuration cfg = new Configuration();
          cfg.configure("/Hibernate.cfg.xml");
          ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
          SessionFactory sf = cfg.buildSessionFactory(registry);
          Session s = sf.openSession();
          Transaction tx = s.beginTransaction();

          PlayerData playerData = new PlayerData();

          playerData.setId(id);
          playerData.setName(name);
          playerData.setMobile(mobile);
          playerData.setEmail(email);

          //map object
          s.save(playerData);
          //commit transaction
          s.flush();
          tx.commit();
          s.close();
      }
}
