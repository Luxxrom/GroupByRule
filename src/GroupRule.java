import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GroupRule {

    public static void main(String[] args) throws IOException {
        List<String> lines1 = Files.readAllLines(Paths.get("\\\\Dom-81\\c$\\JAVA_IdeaProjects\\GroupByRule\\A123.txt"), StandardCharsets.UTF_8);
//        FileInputStream file = new FileInputStream("\\\\Dom-81\\c$\\JAVA_IdeaProjects\\GroupByRule\\A123.txt");
//        InputStreamReader fr = new InputStreamReader(file);
//       BufferedReader reader = new BufferedReader(fr);

//        ArrayList<String> textLines1 = null;
//        ArrayList<String> textRoles1 = null;

        String[] textLines = new String[(int) (lines1.size())-6];
        String[] textRoles = new String[4];

        boolean lines = false, roles = false;

        for (int i = 0, j = 0; i < lines1.size();  i++) {
//            i++;
//            // считаем сначала первую строку
//            int i = 0;

//            String line = reader.readLine();
//            StringBuilder sb = new StringBuilder();
//            while (line != null) {
//            System.out.println(lines1.get(i));
//                // считываем остальные строки в цикле
//                line = reader.readLine();
            if (lines1.get(i).equals("textLines:")) {
                lines = true;
                roles = false;
                continue;
            } else if (lines1.get(i).equals("roles:")) {
                roles = true;
                lines = false;
                continue;
            }
            if (roles) {
//                assert textRoles1 != null;
                textRoles[i-1] = lines1.get(i);
            }
            if (lines) {
                textLines[j] = lines1.get(i);
                j++;
            }
//
        }

//        textRoles1.toArray(textRoles);
//        textLines1.toArray(textLines);
        System.out.println(printTextPerRole(textRoles, textLines));
    }

/*Вам дан список ролей и сценарий пьесы в виде массива строчек.

Каждая строчка сценария пьесы дана в следующем виде:
Роль: текст

Текст может содержать любые символы.

Напишите метод, который будет группировать строчки по ролям, пронумеровывать их и возвращать результат в виде готового текста (см. пример). Каждая группа распечатывается в следующем виде:

Роль:
i) текст
j) текст2
...
==перевод строки==

i и j -- номера строк в сценарии. Индексация строчек начинается с единицы, выводить группы следует в соответствии с порядком ролей. Переводы строк между группами обязательны, переводы строк в конце текста не учитываются.

Заметим, что вам предстоит обработка огромной пьесы в 50 000 строк для 10 ролей – соответственно, неправильная сборка результирующей строчки может выйти за ограничение по времени.*/

    private static String printTextPerRole(String[] roles, String[] textLines) {

        //String resultString = "", temp1 = "";
        StringBuilder resultString = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        int count = 0;
        for (int i = 0; i < roles.length; i++) {
            resultString.append(roles[i]);
            resultString.append(":\n");

            for (int j = 0; j < textLines.length; j++) {
                if (textLines[j].equals("0"))
                    continue;
                else {
                    //читаю автора
                    while (textLines[j].charAt(count) != ':') {
                        temp.append(textLines[j].charAt(count));
                        count++;
                    }
                    //если нужный автор
                    if (roles[i].equals(temp.toString())) {
                        count = count + 2;
                        resultString.append(j + 1);
                        resultString.append(") ");
                        for (; count < textLines[j].length(); count++) {
                            resultString.append(textLines[j].charAt(count));
                        }
                        resultString.append("\n");
                        textLines[j] = "0";

                    }
                    temp.setLength(0);
                    count = 0;

                }
            }
            resultString.append("\n");

        }
        return resultString.toString();
    }

}
