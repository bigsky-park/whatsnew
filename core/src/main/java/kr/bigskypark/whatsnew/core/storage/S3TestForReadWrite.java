//package kr.bigskypark.whatsnew.core.storage;
//
//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.*;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kr.bigskypark.whatsnew.core.Category;
//import kr.bigskypark.whatsnew.core.dto.Item;
//
//import java.io.*;
//
//import static kr.bigskypark.whatsnew.core.storage.S3KeyResolver.resolveForData;
//
//public class S3TestForReadWrite {
//
//    public static void main(String[] args) throws Exception {
//
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
//                .withRegion(Regions.AP_NORTHEAST_2)
//                .build();
//
//        final String s3Bucket = "s3-whatsnew";
//        final String s3Key = resolveForData(Category.BOOK, "20191125", "hello", "world");
//        System.out.println(s3Key);
//
//        ObjectMapper mapper = new ObjectMapper();
//        byte[] bytes = mapper.writeValueAsBytes(item);
//
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType("text/plain");
//        objectMetadata.setContentLength(bytes.length);
//        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Bucket, s3Key, new ByteArrayInputStream(bytes), objectMetadata);
////
////        PutObjectResult putObjectResult = s3.putObject(putObjectRequest);
//
//        S3Object object = s3.getObject(s3Bucket, s3Key);
//        S3ObjectInputStream objectContent = object.getObjectContent();
//        byte[] objectBytes = objectContent.readAllBytes();
//        System.out.println(new String(objectBytes));
//        System.out.println(mapper.readTree(objectBytes));
//        System.out.println(mapper.readValue(objectBytes, Item.class));
//    }
//
//    private static File createSampleFile() throws IOException {
//        File file = File.createTempFile("aws-java-sdk-", ".txt");
//        file.deleteOnExit();
//
//        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
//        writer.write("abcdefghijklmnopqrstuvwxyz\n");
//        writer.write("01234567890112345678901234\n");
//        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
//        writer.write("01234567890112345678901234\n");
//        writer.write("abcdefghijklmnopqrstuvwxyz\n");
//        writer.close();
//
//        return file;
//    }
//
//}
