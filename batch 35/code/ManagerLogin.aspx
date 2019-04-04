<%@ Page Title="" Language="C#" MasterPageFile="~/Home.master" AutoEventWireup="true" CodeFile="ManagerLogin.aspx.cs" Inherits="ManagerLogin" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <p>
        <br />
    </p>
<table style="width: 100%">
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td colspan="2">
            <strong>
            <asp:Label ID="Label1" runat="server" Text="Manager Login"></asp:Label>
            </strong>
        </td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            <asp:Label ID="Label2" runat="server" Text="User Name"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
        </td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            <asp:Label ID="Label3" runat="server" Text="Password"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="TextBox2" runat="server" TextMode="Password"></asp:TextBox>
        </td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/NewMangager.aspx">New Manager</asp:HyperLink>
        </td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            <asp:Button ID="Button1" runat="server" Text="Login" onclick="Button1_Click" />
&nbsp;<asp:Button ID="Button2" runat="server" Text="Clear" onclick="Button2_Click" />
        </td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td style="width: 279px">
            &nbsp;</td>
        <td style="width: 142px">
            &nbsp;</td>
        <td>
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
</table>
    <p>
    </p>
    <p>
    </p>
    <p>
    </p>
</asp:Content>

