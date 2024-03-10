//
//  TagLaunchRow.swift
//  iosApp
//
//  Created by Ekaterina.Petrova on 25.08.2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import allshared

struct TagRow: View {
    var tag:  BreedsTag

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
               Text("Tag name: \(tag.name ?? "empty")")
               Text("Tag type: \(tag.tagType ?? "empty")")
                 Text("Tag id: \(tag.externalId ?? "empty")").foregroundColor(tagColor)
            }
            Spacer()
        }
    }
}

extension TagRow {
   private var tagColor: Color {

       if tag.parentId != "0"  {
           return Color.green
       } else {
           return Color.gray
       }
   }
}
