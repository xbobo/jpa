package com.xiaobo.conf;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.xiaobo.util.UtilsHelper;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class EmotionDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map map, TemplateModel[] temp,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if (!UtilsHelper.isEmpty(map))
			throw new RuntimeException("param must to be empty");

		body.render(new EmotionFilterWriter(env.getOut()));
	}

	private static class EmotionFilterWriter extends Writer {

		private final Writer out;

		EmotionFilterWriter(Writer out) {
			this.out = out;
		}

		public void write(char[] buf, int off, int len) throws IOException {
			String emotion = new String(buf, off, len);
			switch (emotion) {
			case "positive":
				out.write("正面");
				break;
			case "negative":
				out.write("负面");
				break;
			case "neture":
				out.write("中性");
				break;
			default:
				throw new RuntimeException("unknow emotion [" + emotion +"]");
			}
		}

		public void flush() throws IOException {
			out.flush();
		}

		public void close() throws IOException {
			out.close();
		}
	}

}
