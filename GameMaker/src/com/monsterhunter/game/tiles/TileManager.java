package com.monsterhunter.game.tiles;

import com.monsterhunter.game.graphic.Sprite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.util.ArrayList;

public class TileManager {

    public static ArrayList<TileMap> tileMaps;

    public TileManager() {
        tileMaps = new ArrayList<TileMap>();
    }

    public TileManager(String path) {
        tileMaps = new ArrayList<TileMap>();
        addTileMap(path, 64, 64);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) {
        String imagePath = "";
        int width = 0;
        int height = 0;
        int tileWidth = 0;
        int tileHeight = 0;
        int tileCount = 0;
        int tileColumns = 0;
        int layers = 0;
        Sprite sprite;

        ArrayList<String> data = new ArrayList<>(); // Changed from fixed size array to ArrayList for dynamic sizing

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(getClass().getClassLoader().getResourceAsStream(path));
            document.getDocumentElement().normalize();

            // Parsing the tileset attributes
            NodeList list = document.getElementsByTagName("tileset");
            if (list != null && list.getLength() > 0) {
                Node node = list.item(0);
                Element element = (Element) node;

                imagePath = element.getAttribute("name");
                tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
                tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
                tileCount = Integer.parseInt(element.getAttribute("tilecount"));
                tileColumns = Integer.parseInt(element.getAttribute("columns"));

                System.out.println("Tileset attributes: " + imagePath + " " + tileWidth + " " + tileHeight + " " + tileCount + " " + tileColumns);

                sprite = new Sprite("tile/" + imagePath + ".png", tileWidth, tileHeight);
            } else {
                System.out.println("Error: No tileset found in XML.");
                return;
            }

            // Parsing the layers
            list = document.getElementsByTagName("layer");
            layers = list.getLength();
            if (layers > 0) {
                for (int i = 0; i < layers; i++) {
                    Node node = list.item(i);
                    Element element = (Element) node;

                    if (i == 0) {
                        width = Integer.parseInt(element.getAttribute("width"));
                        height = Integer.parseInt(element.getAttribute("height"));
                    }

                    String layerData = element.getElementsByTagName("data").item(0).getTextContent();
                    data.add(layerData); // Add the layer data to the ArrayList

                    if(i >= 1) {
                        tileMaps.add(new TileMapNorm(data.get(i), sprite, width, height, blockWidth, blockHeight, tileColumns));
                    }else{
                        tileMaps.add(new TileMapObj(data.get(i), sprite, width, height, blockWidth, blockHeight, tileColumns));
                    }
                }
            } else {
                System.out.println("Error: No layers found in XML.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g) {
        // Placeholder for rendering logic
        for(int i = 0; i < tileMaps.size(); i++) {
            tileMaps.get(i).render(g);
        }
    }
}
