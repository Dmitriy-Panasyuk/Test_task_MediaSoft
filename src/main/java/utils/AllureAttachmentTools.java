package utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class AllureAttachmentTools {

   @Attachment(value = "data", type = "text/plain", fileExtension = ".txt")
   @Step("Прикрепить текстовый файл")
   public static String attachDataTXT(String str) {
      return str;
   }

}
