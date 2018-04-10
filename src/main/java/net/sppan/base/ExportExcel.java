package net.sppan.base;

import net.sppan.base.entity.StudentStatus;

import java.util.ArrayList;
import java.util.HashMap;

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




}
