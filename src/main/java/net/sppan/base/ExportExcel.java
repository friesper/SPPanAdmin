package net.sppan.base;

import net.sppan.base.entity.StudentStatus;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

public class ExportExcel {
    private ArrayList<StudentStatus> arrayList;
    public ExportExcel(ArrayList<StudentStatus> arrayList){
        this.arrayList=arrayList;
    }
    public String  createFile(){
        HashMap<String ,ArrayList<StudentStatus>> hashMap=new HashMap();
        ArrayList<StudentStatus> arrayList1 =null;
        for (int i=0;i<arrayList.size();i++){
             if (hashMap.containsKey(arrayList.get(i).getStudentName())){
                 hashMap.get(arrayList.get(i).getStudentName());
                 arrayList1.add(arrayList.get(i));
             }else {
                 arrayList1=new ArrayList<>();
                 arrayList1.add(arrayList.get(i));
                 hashMap.put(arrayList.get(i).getStudentName(),arrayList1);
             }
         }
        return null;
    }
    public static void main(String args[]) throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom random = new SecureRandom();
        random.setSeed("123456".getBytes());//设置加密用的种子，密钥
        keyGenerator.init(random);
        SecretKey secretKey = keyGenerator.generateKey();
        //把上面的密钥存起来
        Path keyPath = Paths.get("D:/aes.key");
        Files.write(keyPath, secretKey.getEncoded());
    }



}
