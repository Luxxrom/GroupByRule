import java.io.*;
import java.util.ArrayList;

public class GroupRule {

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream file = new FileInputStream("\\\\TMN-STS-UI4\\C$\\JAVA_Projects\\GroupByRule\\A123.txt");
        InputStreamReader fr = new InputStreamReader(file);
        BufferedReader reader = new BufferedReader(fr);

        String[] textLines = new String[(int) (reader.lines().count() - 1)];
        String[] textRoles = new String[5];

        try {
            // считаем сначала первую строку
            int i = 0;
            boolean lines = false, roles = false;

            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
//                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line.equals("textLines:")) {
                    lines = true;
                    roles = false;
                } else if (line.equals("roles:")) {
                    roles = true;
//                lines = false;
                }
                if (roles) {
                    textRoles[i] = line;
                }
                if (lines) {
                    textLines[i] = line;
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
