package org.mpii.jami.test

import org.mpii.jami.CompleteRun
import spock.lang.Specification

/**
 * Created by mlist on 10/26/17.
 */
class TestSelectGene extends Specification {

    def genesMiRNA = new File("data/10_genes_mirna_interactions_triplet_format.txt")
    def fileGeneExpr = new File("data/10_genes_gene_expr.txt")
    def filemiRNAExpr = new File("data/10_genes_mir_expr.txt")

    def test_dir = new File("out/test").mkdir()

    def "test select gene"()
    {
        given:
        def outputFileName = new File("out/test/test_p_value_cutoff.csv")

        when:
        CompleteRun completeRun = new CompleteRun(genesMiRNA,fileGeneExpr,filemiRNAExpr, outputFileName)
        completeRun.numberOfPermutations = 100
        completeRun.filterForGene("ENSG00000110427")
        completeRun.runComputation()

        then:
        completeRun.completed == true
        completeRun.tripletsWrittenToDisk == 40
    }
}