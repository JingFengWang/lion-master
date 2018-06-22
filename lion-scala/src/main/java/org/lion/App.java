package org.lion;

import scala.collection.JavaConversions;
import scala.collection.JavaConverters;
import scala.collection.mutable.Buffer;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        scala.collection.Seq<String> a = null;
        List<String> list = JavaConversions.seqAsJavaList(a);
        List<String> b = null;
        Buffer<String> buffer = JavaConverters.asScalaBufferConverter(b).asScala();
        System.out.println( "Hello World!" );
    }
}
