package kh.semi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

class Score {
   private String name;
   private int score;

   public Score() {}
   public Score(int score, String name) {
      this.score = score;
      this.name = name;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getScore() {
      return score;
   }
   public void setScore(int score) {
      this.score = score;
   }

}

public class ScoreRanking {
   public static ArrayList<Score> rankList = new ArrayList<>();   // 기록 저장소 
   ArrayList<Score> tempList = new ArrayList<>();
   private File file;
   private boolean isFirst = true;
   boolean isEmpty;

   public int setSize(ArrayList list) {
      int size = list.size();
      if(list.size() >= 5) {
         size = 5;
      } else if(list.size() == 0){
         size = 0;
      } else {
         size = list.size();
      }
      return size;
   }

   public void readTxt() {   // 텍스트파일 읽어와서 리스트에 저장
      try(BufferedReader br = new BufferedReader(new FileReader("src/kh/semi/resources/Ranking.txt"));) {
         rankList.clear();
         while(true) {
            String line = br.readLine();
            if(line == null) {
               if(isFirst) {   
                  isEmpty = true;   // 처음 읽어온게 null이면 ArrayList 비어있음 
                  break;
               } else { break; }
            } else {
               Score s = new Score();
               s.setName(line.split(":")[0].split("=")[1]);
               s.setScore(Integer.parseInt((line.split(":")[1].split("=")[1])));
               rankList.add(s);
               isFirst = false;
               isEmpty = false;
            }
         }

      } catch(Exception e) {
         e.printStackTrace();
      }
   }

   public void renewRank(int score, String name) {   // 랭크 갱신 
      int size = setSize(rankList);
      if(size == 0) {
         tempList.add(new Score(score,name));   // ArrayList 비어있으면 바로 기록
      } else {
         boolean loop = true;
         boolean bool = true;
         for(int i = 0; i < size; i++) {
            if(score < rankList.get(i).getScore()) {   
               tempList.add(rankList.get(i));
            } else if(score >= rankList.get(i).getScore()) {
               tempList.add(new Score(score,name));
               bool = false;
               int j = i;
               while(j < size) {
                  tempList.add(rankList.get(j));
                  System.out.println("기존 : " + rankList.get(j).getName() + ", " + rankList.get(j).getScore() );
                  j++;
                  loop = false;
               }
            }
            if(!loop) { break; }
         }
         
         if(size < 5 && bool) {
            tempList.add(new Score(score,name));
         }
      }
      rankList.clear();
      int tempSize = setSize(tempList);
      for(int i = 0; i < tempSize; i++) {
         rankList.add(tempList.get(i));
      }
   }

   public void saveTxt() {   // 파일로 내보내기 
      file = new File("src/kh/semi/resources/Ranking.txt");
      try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
         for(Score tmp : rankList) {
            bw.write("name=" + tmp.getName() + ":score=" + tmp.getScore());
            bw.newLine();
         }
         System.out.println("랭킹 저장");
         GameOverDialog.start = false;
      } catch(Exception e) {
         e.printStackTrace();
      }
   }



   public ScoreRanking(int score, String name) {   // 매개변수받아와서 기록 
      if(GameOverDialog.start) {
         renewRank(score, name);
         saveTxt();
      }
   }

   public ScoreRanking() {   // 매개변수 없이 생성하면 txt파일 읽어와서 ArrayList에 저장 
      this.readTxt();
   }
}