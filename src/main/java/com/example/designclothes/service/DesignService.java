package com.example.designclothes.service;
import com.example.designclothes.domain.Design;
import com.example.designclothes.repository.DesignRepository;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Transactional
public class DesignService {
    private final DesignRepository designRepository;
    public DesignService(DesignRepository designRepository){
        this.designRepository = designRepository;
    }
    public Long saveDesignInfo(String userName, String fileRoute, Integer price){
        Design design = new Design();
        design.setUserName(userName);
        design.setPrice(price);
        design.setFileRoute(fileRoute);
        designRepository.save(design);
        return design.getId();
    }
    public String saveDesign(String userName, String photoData) throws IOException {
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(photoData);
        String fileName = System.currentTimeMillis()+"_"+userName+".png";
        String url = "./design/"+fileName;
        File file = new File(url);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        outputStream.write(imageBytes);

        return url;
    }
}
