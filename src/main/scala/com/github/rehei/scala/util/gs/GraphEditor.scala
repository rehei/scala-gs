package com.github.rehei.scala.util.gs

import org.graphstream.graph.implementations.SingleGraph
import org.graphstream.graph.Node
import org.graphstream.graph.Edge
import collection.JavaConversions._
import java.awt.Color
import org.graphstream.ui.layout.HierarchicalLayout
import org.graphstream.ui.view.Viewer
import org.graphstream.ui.swingViewer.basicRenderer.SwingBasicGraphRenderer

class GraphEditor {

  protected val UI_LABEL = "ui.label"
  protected val UI_COLOR = "ui.color"
  protected val UI_STYLESHEET = "ui.stylesheet"

  protected val graph = new SingleGraph("tutorial 1")
  graph.setStrict(false)
  graph.setAutoCreate(true)
  graph.addAttribute(UI_STYLESHEET, "node { text-size: 18; size: 10px; fill-mode: dyn-plain; } edge { text-size: 14; }");

  protected var i = 0;

  def draw() {
    graph.display(true)
  }

  def drawHierarchically(node: Node) {
    val viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD)
    val renderer = new SwingBasicGraphRenderer()
    viewer.addView(Viewer.DEFAULT_VIEW_ID, renderer)
    val layout = new HierarchicalLayout()
    layout.setRoots(node.getId)
    viewer.enableAutoLayout(layout)
  }

  def addNode(label: String) = {
    try {
      val node: Node = graph.addNode(label)
      node.addAttribute(UI_LABEL, label)
      node.addAttribute(UI_COLOR, Color.RED);
      node
    } catch {
      case ex: org.graphstream.graph.IdAlreadyInUseException => graph.getNode(label)
    }
  }

  def addEdge(source: Node, drain: Node, labelOption: Option[String]) {
    val id = source.getId() + "---" + drain.getId()
    try {
      val edge: Edge = graph.addEdge(id, source, drain)

      for (label <- labelOption) {
        edge.addAttribute(UI_LABEL, label)
      }
    } catch {
      case ex: NullPointerException => ex.printStackTrace()
    }
  }

  protected def generateID() = {
    i = i + 1
    i.toString()
  }

}