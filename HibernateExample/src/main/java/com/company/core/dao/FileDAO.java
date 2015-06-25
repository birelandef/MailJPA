package com.company.core.dao;//package com.company.core.dao;
//
//import com.company.core.api.Constants;
//import com.company.core.entity.Attachment;
//import com.company.core.entity.Entity;
//import com.company.core.factory.entities.*;
//import org.apache.log4j.Logger; // Счастье
//
//import java.io.*;
//import java.math.BigInteger;
//import java.util.Map;
//
///**
// * Created by Sophie on 25.03.2015.
// */
//public class FileDAO<T extends Entity> extends MemoryDAO<T> {
//
//    private static final Logger log = Logger.getLogger(FileDAO.class);
//
//    private static File filePerson = new File(Constants.PATHTORESOURCES + "Person" + ".mail");
//    private static File fileAccount = new File(Constants.PATHTORESOURCES + "Account" + ".mail");
//    private static File fileContact = new File(Constants.PATHTORESOURCES + "Contact" + ".mail");
//    private static File fileFolder = new File(Constants.PATHTORESOURCES + "Folder" + ".mail");
//    private static File fileLetter = new File(Constants.PATHTORESOURCES + "Letter" + ".mail");
//    private static File fileAttachment = new File(Constants.PATHTORESOURCES + "Attachment" + ".mail");
//
//    private static File[] files = {filePerson, fileAccount, fileContact, fileFolder, fileLetter, fileAttachment};
//
//    /**
//     * Constructor which initializes all files for store info about entities
//     * @throws IOException if any file didn't create
//     */
//    public FileDAO() throws IOException {
//        new File(Constants.PATHTORESOURCES).mkdirs();
//        for (File f: files){
//            f.createNewFile();
//        }
//    }
//
//    @Override
//    public BigInteger create(T entity) {
//        BigInteger id = super.create(entity);
//        try {
//            File outFile = chooseFile(entity.getClass().getSimpleName());
//            serialized(entity, new FileOutputStream(outFile));
//        } catch (IOException e) {
//            log.error("Error has occurred ", e);
//        }
//        return id;
//    }
//
//    @Override
//    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException {
//        T obj = findById(id);
//        super.update(id, parameters);
//        create(obj);
//    }
//
//    @Override
//    public boolean delete(BigInteger id) {
//        T obj = findById(id);
//        super.delete(id);
//        try {
//            Map map = null;
//            switch (obj.getClass().getSimpleName()){
//                case "Person": map = persons;
//                    break;
//                case "Account": map = accounts;
//                    break;
//                case "Contact": map = contacts;
//                    break;
//                case "Folder": map = folders;
//                    break;
//                case "Letter": map = letters;
//                    break;
//                case "Attachment": map = attachments;
//                    break;
//                default: throw new InvalidObjectException("Type mismatch");
//            }
//            //TODO как-то записать иначе
//            for(Map.Entry<BigInteger, T> entry : map.entrySet()){
//                create((T) entry.getValue());
//            }
//        } catch (IOException e) {
//            log.error("Error has occurred ", e);
//        }
//        return true;
//    }
//
//    @Override
//    public T findById(BigInteger id) {
//        read();
//        return super.findById(id);
//    }
//
//    /**
//     * Read info about all instances of entities from files
//     */
//    private void read(){
//        Person p = null;
//        Account ac = null;
//        Attachment at = null;
//        Folder fol  = null;
//        Contact c = null;
//        Letter l = null;
//        String nameFile = null;
//        for (File f: files){
//            if (f.length() != 0){
//                nameFile = f.getName().substring(0, f.getName().lastIndexOf("."));
//                try {
//                    switch (nameFile){
//                        case "Person":
//                            p  =  deserialized(new FileInputStream(filePerson));
//                            persons.put(p.getId(), p);
//                            break;
//                        case "Account":
//                            ac = deserialized(new FileInputStream(fileAccount));
//                            accounts.put(ac.getId(), ac);
//                            break;
//                        case "Attachment":
//                            at  =  deserialized(new FileInputStream(fileAttachment));
//                            attachments.put(at.getId(), at);
//                            break;
//                        case "Folder":
//                            fol  =  deserialized(new FileInputStream(fileFolder));
//                            folders.put(fol.getId(), fol);
//                            break;
//                        case "Contact":
//                            c  =  deserialized(new FileInputStream(fileContact));
//                            contacts.put(c.getId(), c);
//                            break;
//                        case "Letter":
//                            l  =  deserialized(new FileInputStream(fileLetter));
//                            letters.put(l.getId(), l);
//                            break;
//
//                    }
//                    //Эти ошибки вряд ли выпадут
//                } catch (IOException e) {
//                    log.error(e);
//                } catch (ClassNotFoundException e) {
//                    log.error(e);
//                }
//            }
//        }
//    }
//
//    /**
//     * Choose file which would be append new instance
//     * @param clazz name of instance's class
//     * @return file which corresponds to the instance
//     * @throws InvalidObjectException if such file don't exists (absence such class)
//     */
//    private File chooseFile(String clazz) throws InvalidObjectException {
//            File outFile = null;
//                switch (clazz){
//                    case "Person": outFile = filePerson;
//                        break;
//                    case "Account": outFile = fileAccount;
//                        break;
//                    case "Contact": outFile = fileContact;
//                        break;
//                    case "Folder": outFile = fileFolder;
//                        break;
//                    case "Letter": outFile = fileLetter;
//                        break;
//                    case "Attachment": outFile = fileAttachment;
//                        break;
//                    default: throw new InvalidObjectException("Type mismatch");
//                }
//        return  outFile;
//    }
//
//    public void serialized(Object obj, FileOutputStream out) throws IOException {
//        try (ObjectOutputStream oout = new ObjectOutputStream(out)){
//            oout.writeObject(obj);
//        }
//    }
//
//    public  <T extends Entity> T deserialized(FileInputStream in) throws IOException, ClassNotFoundException {
//        ObjectInputStream oin = null;
//        try{
//            oin = new ObjectInputStream(in);
//            T obj  = (T) oin.readObject();
//            return  obj;
//        } finally {
//            oin.close();
//            in.close();
//        }
//    }
//}
