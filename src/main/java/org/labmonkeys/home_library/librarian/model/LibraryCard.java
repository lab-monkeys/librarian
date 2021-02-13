package org.labmonkeys.home_library.librarian.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "library_card", indexes = {@Index(name = "idx_library_card_id", columnList = "library_card_id")})
public class LibraryCard extends PanacheEntityBase {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(updatable = false)
    private Long libraryCardId;

    @Column()
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "library_member", nullable = false)
    private LibraryMember libraryMember;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "libraryCard", cascade = CascadeType.ALL)
    @OrderBy("dueDate ASC")
    private List<BorrowedBook> borrowedBooks;
}