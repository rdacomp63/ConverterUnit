package org.company;
import org.junit.Test;
import static junit.framework.Assert.*;


/*
        1024 byte = 1 kilobyte 
        2 bar = 12 ring 
        16.8 ring = 2 pyramid 
        4 hare = 1 cat 
        5 cat = 0.5 giraffe 
        1 byte = 8 bit
        15 ring = 2.5 bar 

        1 pyramid = ? bar 
        1 giraffe = ? hare 
        0.5 byte = ? cat 
        2 kilobyte = ? bit 
 */

/**
 * Unit test for ConverterUnit.
 */
public class AppTest 
{
    Container container = Container.getInstance();

    @Test
    public void testFind1() throws Exception {
        container.Clear();
        Float value = null;

        AddContainer("byte", "kilobyte", 1024f, 1f);
        AddContainer("bar", "ring", 2f, 12f);
        AddContainer("ring", "pyramid", 16.8f, 2f);
        AddContainer("hare", "cat", 1f, 4f);
        AddContainer("cat", "giraffe",5f, 0.5f);
        AddContainer("byte", "bit", 1f, 8f);
        AddContainer("ring", "bar", 15f, 2.5f);

        value = container.Find("pyramid", "bar");
        // Проверяемый результат
        assertEquals("Norm", 1.4f, value);

        value = container.Find("giraffe", "hare");
        assertEquals("Norm", 2.5f, value);

        value = container.Find("byte", "cat");
        assertEquals("Norm", null, value);

        value = container.Find("kilobyte", "bit");
        assertEquals("Norm", 8192.0f, value);
    }

    @Test
    public void testFind2() throws Exception {
        container.Clear();
        Float value = null;

        AddContainer("a", "b", 1f, 2f);
        AddContainer("a", "c", 1f, 4f);

        // Проверяемый результат
        value = container.Find("b", "c");
        assertEquals("Error b ? c", 2.0f, value);

        AddContainer("b", "c", 1f, 2f);

        value = container.Find("c", "b");
        assertEquals("Error c ? b", 0.5f, value);

        try {
            value = container.Find("c", "d");
            assertEquals("Error c ? d", null, value);
        }
        catch (Exception e) {
            System.out.println("Error c ? d");
        }
    }

    // Тройной поискб ошибка алгоритм не находит решение
    @Test
    public void testFind3() throws Exception {
        container.Clear();
        Float value = null;

        AddContainer("a", "b", 1f, 2f);
        AddContainer("b", "c", 1f, 2f);
        AddContainer("c", "d", 1f, 2f);

        // Проверяемый результат
        value = container.Find("a", "d");
        assertEquals("Error a ? d", 2.0f, value);
    }

    /**
     * Add container data
     */
    private void AddContainer(String typeA, String typeB, Float valueA, Float valueB) {
        // Добавить в коллекцию прямое и обратное преобразование для ускорения поиска;
        container.Add(typeA, typeB, valueB/valueA);
        container.Add(typeB, typeA, valueA/valueB);
    }

}
