package com.unip.tcc.scarblade.reconhecimento.test;

import java.io.File;

import java.io.File;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import org.bytedeco.opencv.opencv_face.*; 
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;

/**
 *
 * @author PC
 */
public class Teste {
    
	public static void main(String[] args) {
        int totalAcertos = 0;
        double percentualAcerto = 0;
        double totalConfianca = 0;
        
        //FaceRecognizer reconhecedor = EigenFaceRecognizer.create();
        //FaceRecognizer reconhecedor = FisherFaceRecognizer.create();
        FaceRecognizer reconhecedor = LBPHFaceRecognizer.create();

        //reconhecedor.read("src\\recursos\\classificadorEigenfacesYale.yml");
        //reconhecedor.read("src\\recursos\\classificadorFisherfacesYale.yml");
        reconhecedor.read("D:\\DEV\\Projetos\\scarblade-treinamento\\src\\recursos\\classificadorLBPH.yml");

        File diretorio = new File("D:\\DEV\\Projetos\\scarblade-treinamento\\fotos\\");
        File[] arquivos = diretorio.listFiles();
        
        for (File imagem : arquivos) {           
        	 Mat foto = imread(imagem.getAbsolutePath(), IMREAD_GRAYSCALE);
             int classe = Integer.parseInt(imagem.getName().split("\\.")[1]);        
            resize(foto, foto, new Size(160, 160));

            IntPointer rotulo = new IntPointer(1);
            DoublePointer confianca = new DoublePointer(1);
            reconhecedor.predict(foto, rotulo, confianca);
            int predicao = rotulo.get(0);
            System.out.println(imagem.getAbsolutePath() +" foi pelo ID: " + predicao + " - " + confianca.get(0));
            if (classe == predicao) {
                totalAcertos++;
                totalConfianca += confianca.get(0);
            }
        }
        
        percentualAcerto = (totalAcertos / 30.0) * 100;
        totalConfianca = totalConfianca / totalAcertos;
        System.out.println("Percentual de acerto: " + percentualAcerto);
        System.out.println("Total confiança: " + totalConfianca);
    }
    
}
