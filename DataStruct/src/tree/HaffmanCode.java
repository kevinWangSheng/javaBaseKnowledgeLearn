package tree;

import java.io.*;
import java.util.*;

/**
 * @author wang
 * @create 2023-2023-03-19:58
 */
public class HaffmanCode {
    private StringBuilder builder = new StringBuilder();
    private String str = "sdfawef asefjeorgj sfwiaofj ";
    private Map<Byte,String> map = new HashMap<>();

    public static void main(String[] args) {
        HaffmanCode haffmanCode = new HaffmanCode();
//
//        HaffmanCodeNode root = haffmanCode.createHaffmanCode(haffmanCode.str);
//
//        haffmanCode.preOrder(root);
//
//        haffmanCode.getHaffmanCodeMap(root);
//        System.out.println(haffmanCode.map);
//
//        System.out.println(haffmanCode.getHaffmanStringCode(root));
//
//        System.out.println(Arrays.toString(haffmanCode.getHaffmanByte(haffmanCode.builder)));

//        System.out.println(Arrays.toString(haffmanCode.getHaffmanByte()));
//        byte[] decode = haffmanCode.decode(haffmanCode.getHaffmanByte(haffmanCode.str.getBytes()));

//        System.out.println(new String(decode));

        haffmanCode.zipFile("DataStruct/src/file/baoguo.png","DataStruct/src/file/baoguo1.zip");
        haffmanCode.unzip("DataStruct/src/file/baoguo1.zip","DataStruct/src/file/unzipbaoguo.png");
    }

    public List<HaffmanCodeNode> getHaffmanList(byte[] bytes){

        Map<Byte,Integer> map = new HashMap<>();
        for(byte b:bytes){
            Integer elem = map.get(b);
            if(elem==null){
                map.put(b,1);
            }else{
                map.put(b,map.get(b)+1);
            }
        }

        List<HaffmanCodeNode> haffmanCodes = new ArrayList<>();

        for(Map.Entry<Byte,Integer> entry : map.entrySet()){
            haffmanCodes.add(new HaffmanCodeNode(entry.getValue(), entry.getKey()));
        }
        Collections.sort(haffmanCodes);
        return haffmanCodes;
    }

    public HaffmanCodeNode createHaffmanCode(byte[] bytes){
        List<HaffmanCodeNode> haffmanList = getHaffmanList(bytes);
        while(haffmanList.size()>1){
            HaffmanCodeNode left = haffmanList.get(0);
            HaffmanCodeNode right = haffmanList.get(1);

            HaffmanCodeNode node = new HaffmanCodeNode(left.getCount() + right.getCount(), null);

            node.setLeft(left);
            node.setRight(right);
            haffmanList.add(node);

            haffmanList.remove(left);
            haffmanList.remove(right);

            Collections.sort(haffmanList);
        }

        return haffmanList.get(0);
    }

    public void preOrder(HaffmanCodeNode root){
        if(root == null){
            return ;
        }
        System.out.println(root);
        if(root.getLeft()!=null){
            preOrder(root.getLeft());
        }
        if(root.getRight()!=null){
            preOrder(root.getRight());
        }
    }

    public Map<Byte,String> getHaffmanCodeMap(HaffmanCodeNode node,int code,StringBuilder builder){
        StringBuilder builder2 = new StringBuilder(builder);
        builder2.append(code);
        if(node!=null) {
            if (node.getData() == null) {
                getHaffmanCodeMap(node.getLeft(), 0, builder2);
                getHaffmanCodeMap(node.getRight(), 1, builder2);
            } else {
//                System.out.println(builder2);
                map.put(node.getData(), builder2.toString());
            }
        }
        return map;
    }

    public Map<Byte,String> getHaffmanCodeMap(HaffmanCodeNode node){
        StringBuilder builder = new StringBuilder();

        if(node == null){
            return null;
        }
        if(node.getLeft()!=null){
            getHaffmanCodeMap(node.getLeft(),0,builder);
        }
        if(node.getRight()!=null){
            getHaffmanCodeMap(node.getRight(),1,builder);
        }
        return map;
    }

    public StringBuilder getHaffmanStringCode(HaffmanCodeNode node,int code,StringBuilder builder1){
        if(node == null){
            return null;
        }
        StringBuilder builder2 = new StringBuilder(builder1);
        builder2.append(code);
        if(node.getData()==null){
            getHaffmanStringCode(node.getLeft(),0,builder2);
            getHaffmanStringCode(node.getRight(),1,builder2);
        }else{
            builder.append(builder2);
        }
        return builder;
    }

    public StringBuilder getHaffmanStringCode(HaffmanCodeNode node){
        if(node == null){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if(node.getLeft() !=null){
            getHaffmanStringCode(node.getLeft(),0,stringBuilder);
        }
        if(node.getRight()!=null){
            getHaffmanStringCode(node.getRight(),1,stringBuilder);
        }
        return builder;
    }

    public byte[] getHaffmanByte(byte[] bytes,Map<Byte,String> map){


        StringBuilder builder = new StringBuilder();

        for(int i = 0;i<bytes.length;i++){
            builder.append(map.get(bytes[i]));
        }

        String str = builder.toString();

        // the byte[] 's len
        int len = 0;
        len = (str.length()+7)/8;

        byte[] haffmanBytes = new byte[len];

        int index = 0;
        String substring = null;
        for(int i = 0;i<str.length();i+=8){
            if(i+8>=str.length()){
                substring = str.substring(i);
                haffmanBytes[index++] = (byte) Integer.parseInt(substring,2);
            }else{
                substring = str.substring(i,i+8);
                haffmanBytes[index++] = (byte) Integer.parseInt(substring,2);
            }
        }
        return haffmanBytes;
    }

    public byte[] getHaffmanByteTotal(byte[] bytes){


        HaffmanCodeNode root = createHaffmanCode(bytes);

        Map<Byte, String> haffmanCodeMap = getHaffmanCodeMap(root);

        return getHaffmanByte(bytes,haffmanCodeMap);
    }

    public String byteToBitString(byte b,boolean flag){
        int temp = b;
        if(flag){
            temp |=256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }

    public byte[] decode(byte[] bytes,Map<Byte,String >map){
        StringBuilder builder = new StringBuilder();

        for(int i = 0;i<bytes.length;i++){
            boolean flag = (i==bytes.length-1);
            builder.append(byteToBitString(bytes[i], !flag));
        }

        Map<String,Byte> newMap = new HashMap<>();

        for(Map.Entry<Byte,String> entry:map.entrySet()){
            newMap.put(entry.getValue(),entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for(int i = 0;i<builder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while(flag){
                if(i==builder.length()-1)
                {
                    break;
                }
                String key = builder.substring(i, i + count);
                b = newMap.get(key);
                if(b!=null){
                    flag = false;
                }else{
                    count++;
                }
            }
            list.add(b);
            i+=count;
        }

        byte[] resB = new byte[list.size()];

        for(int i = 0;i<list.size();i++){
            resB[i] = list.get(i);
        }
        return resB;
    }

    public void zipFile(String srcFile,String objFile){
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);

            byte[] fileByte = new byte[is.available()];

            is.read(fileByte);

            HaffmanCodeNode root = createHaffmanCode(fileByte);
            Map<Byte, String> haffmanCodeMap = getHaffmanCodeMap(root);
            byte[] haffmanByte = getHaffmanByte(fileByte,haffmanCodeMap);

            oos = new ObjectOutputStream(new FileOutputStream(objFile));
            oos.writeObject(haffmanByte);
            oos.writeObject(haffmanCodeMap);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void unzip(String inputFile,String destFile){
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(inputFile));
            fos = new FileOutputStream(destFile);
            byte[] bytes  = (byte[]) ois.readObject();
            Map<Byte,String> map = (Map<Byte, String>) ois.readObject();

            byte[] decoFile = decode(bytes, map);
            fos.write(decoFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{

            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("file close fail");
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println("file close fail");
                    e.printStackTrace();
                }
            }
        }
    }
}
